package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.systemMapper;
import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.R;
import com.gabe.mychat.util.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    userMapper userMapper;
    @Autowired
    systemMapper systemMapper;
    @Autowired
    private Producer producer;
    /**
     * 认证异常
     * <p>
     * org.apache.shiro.authc.pam.UnsupportedTokenException 身份令牌异常，不支持的身份令牌
     * org.apache.shiro.authc.UnknownAccountException       未知账户/没找到帐号,登录失败
     * org.apache.shiro.authc.LockedAccountException        帐号锁定
     * org.apache.shiro.authz.DisabledAccountException      用户禁用
     * org.apache.shiro.authc.ExcessiveAttemptsException    登录重试次数，超限。只允许在一段时间内允许有一定数量的认证尝试
     * org.apache.shiro.authc.ConcurrentAccessException     一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许多处登录
     * org.apache.shiro.authz.AccountException              账户异常
     * org.apache.shiro.authz.ExpiredCredentialsException   过期的凭据异常
     * org.apache.shiro.authc.IncorrectCredentialsException 错误的凭据异常
     * org.apache.shiro.authc.CredentialsException          凭据异常
     * org.apache.shiro.authc.AuthenticationException       上面异常的父类
     */
    @ResponseBody
    @ArchivesLog(operationName = "登录",operationType = "用户基本操作")
    @RequestMapping("/login" )
    public R login(@RequestBody Map<String,String> map, HttpSession session){
        System.out.println("进入登录...");
        String username=map.get("username");
        String password=map.get("password");
        String code=map.get("code");
 /*       String kaptcha= ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptcha.equalsIgnoreCase(code)){
            return R.error("验证码不正确");
        }*/
      //认证异常处理
        try {
            Subject subject= ShiroUtils.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            subject.login(token);
        } catch (UnknownAccountException e) {//错误的账号
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {//错误的凭证
            return R.error(e.getMessage());
        }catch (LockedAccountException e) {//锁定的账号
            return R.error(e.getMessage());
        }catch (AuthenticationException e) {//以上的父类
            return R.error("账户验证失败");
        }
        session.setAttribute("user",ShiroUtils.getUserEntity());
        session.setAttribute("id",ShiroUtils.getUserEntity().getUserId());
        Map<String,Object> msg=new HashMap<>();
        msg.put("code","0");
        msg.put("name",ShiroUtils.getUserEntity().getPassword());
        return R.ok().put("data",msg);
    }

/*    *
     * 生成图形验证码
     * @param response
     * @throws ServletException
     * @throws IOException*/

    @ArchivesLog(operationName = "生成验证码",operationType = "用户基本操作")
    @GetMapping("/imgCode")
    public void captcha(HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session（注意：如果没有securityManager配置，则暂时无法工作，测试时先注释掉）
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
    }
}
