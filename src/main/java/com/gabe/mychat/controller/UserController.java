package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.normalUserUtilMapper;
import com.gabe.mychat.mapper.systemMapper;
import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.mapper.userUtilMapper;
import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.util.*;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author wsw
 * @Package com.gabe.mychat.controller
 * @Description:
 * @date 2019年6月21日 15:21:34
 */
@Controller
public class UserController {
    @Autowired
    private userMapper userMapper;
    @Autowired
    private systemMapper systemMapper;
    @Autowired
    private Producer producer;
    @Autowired
    private userUtilMapper userUtilMapper;
    @Autowired
    private normalUserUtilMapper normalUserUtilMapper;
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
        String kaptcha= ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptcha.equalsIgnoreCase(code)){
            return R.error("验证码不正确");
        }
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
        msg.put("id",ShiroUtils.getUserEntity().getUserId());
        msg.put("nickname",ShiroUtils.getUserEntity().getNickname());
        msg.put("imgurl",ShiroUtils.getUserEntity().getImgurl());
        return R.ok().put("data",msg);
    }

    /*
     * 生成图形验证码
     */
    @ResponseBody
    @ArchivesLog(operationName = "获取验证码",operationType = "用户基本操作")
    @RequestMapping("/imgCode" )
    public R code(){
        System.out.println("获取验证码");
        //生成文字验证码
        String text = producer.createText();
        System.out.println("验证码是:"+text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        //保存到shiro session（注意：如果没有securityManager配置，则暂时无法工作，测试时先注释掉）
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        //转base64
        BASE64Encoder encoder = new BASE64Encoder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        try {
            ImageIO.write(image, "png", baos);//写入流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();//转换成字节
        String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
        //删除 \r\n
        png_base64 = "data:image/jpeg;base64,"+png_base64.replaceAll("\n", "").replaceAll("\r", "");
        return R.ok().put("data",png_base64);
    }

    /**
     * 获取手机验证码
     *
     * @param map data of tel
     * @return R
     */
    @ResponseBody
    @ArchivesLog(operationName = "获取手机验证码", operationType = "用户基本操作")
    @RequestMapping("/telCode")
    public R getTelCode(@RequestBody Map<String, String> map) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append(Math.round(Math.random() * 10));
        }
        return R.ok().put("data", code);
    }

    /**
     * 用户注册
     * @param map data of tel. code, username, pass, password
     * @param session session
     * @return R
     */
    @ResponseBody
    @ArchivesLog(operationName = "注册", operationType = "用户基本操作")
    @RequestMapping("/register")
    public R register(@RequestBody Map<String, String> map, HttpSession session) {
        System.out.println("进入注册...");
        String tel = map.get("tel");
        String code = map.get("code");
        String username = map.get("username");
        String pass = map.get("pass");
        String password = map.get("password");
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equalsIgnoreCase(code)) {
            return R.error("验证码不正确");
        }
        if(NumberUtil.getNumberFromString(tel).length() != 11){
            return R.error("电话号码格式不正确");
        }
        Map<String, Object> msg = new HashMap<>();
        msg.put("code", "0");
        msg.put("id", ShiroUtils.getUserEntity().getUserId());
        return R.ok().put("data", msg);
    }


    /**
     * 查询信息
     * @param reMap
     * @return
     */
    @ResponseBody
    @ArchivesLog(operationType = "查询信息", operationName = "根据用户昵称或id查询用户")
    @RequestMapping(value = "/selectUserByNickName")
    public Map<String,Object> selectUserByNickName(@RequestBody Map<String,Object> reMap) {
               //接收参数
                String nickname=(String)reMap.get("value");
                List list=new ArrayList();
        /*        //判断长度是否是12位，如果不是则为昵称查询
                if(nickname.length()!=12){
                    user user=userUtilMapper.selectUserByNickName(nickname);
                    normalUser normalUser=normalUserUtilMapper.selectUserById(user.getUserId());
                    Map map=new HashMap();
                    map= UserUtil.completeUser(user,normalUser);
                    return R.ok().put("data",map);
                }else {*/
                    //长度为12且不全为数字，则是昵称查询
                    if(NumberUtil.getNumberFromString(nickname).length()!=12){
                        user user=userUtilMapper.selectUserByNickName(nickname);
                        normalUser normalUser=normalUserUtilMapper.selectUserById(user.getUserId());
                        Map map=new HashMap();
                        map= UserUtil.completeUser(user,normalUser);
                        list.add(map);
                        return R.ok().put("data",list);
                    }
                    //长度为12且为数字，则是id查询
                    else {
                        user user=userMapper.selectByPrimaryKey(nickname);
                        normalUser normalUser=normalUserUtilMapper.selectUserById(user.getUserId());
                        Map map=new HashMap();
                        map= UserUtil.completeUser(user,normalUser);
                        list.add(map);
                        return R.ok().put("data",list);
                    }
             /*   }*/
    }
}
