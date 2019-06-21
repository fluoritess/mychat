package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.util.R;
import com.gabe.mychat.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    userMapper userMapper;
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
    @RequestMapping("/login.action" )
    public R login(@RequestBody Map<String,String> map, HttpSession session){
        System.out.println("进入登录...");
        List<user> userList=userMapper.selectByExample(null);

        String username=map.get("name");
        String password=map.get("password");
        String code=map.get("code");

      /*  String kaptcha= ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
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
}
