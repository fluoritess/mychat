package com.gabe.mychat.controller;

import com.gabe.mychat.pojo.user;
import com.gabe.mychat.service.RegisterService;
import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.R;
import com.gabe.mychat.util.ShiroUtils;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 上午 11:00
 * @since jdk
 */
@Controller
public class RegisterContoller {

    @Autowired
    private RegisterService registerService;

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
        System.out.println("开始发送手机验证码");
        String tel = map.get("tel");
        System.out.println("手机号为：" + tel);
        if (tel.length() != 11) {
            return R.error("手机号格式不正确");
        } else {
            for (char c : tel.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return R.error("手机号格式不正确");
                }
            }
        }
        if (!registerService.checkRegister(tel)) {
            return R.error("该手机号已被注册");
        }
        String code = registerService.sendTelCode(tel);
        if(code != null){
            ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            return R.ok();
        }else {
            return R.error("发送手机验证码失败");
        }
    }

    /**
     * 用户注册
     *
     * @param map     data of tel. code, username, pass, password
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
        if (kaptcha == null || !kaptcha.equalsIgnoreCase(code)) {
            return R.error("手机验证码不正确");
        }
        if (tel.length() != 11) {
            return R.error("手机号格式不正确");
        } else {
            for (char c : tel.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return R.error("手机号格式不正确");
                }
            }
        }
        if (!pass.equals(password)) {
            return R.error("两次输入密码不同");
        }
        if (!registerService.checkRegister(tel)) {
            return R.error("该手机号已被注册");
        }
        int res = registerService.userRegister(new user(null, null, username, null, tel, password));
        Map<String, Object> msg = new HashMap<>(2);
        if(res != 0){
            return R.ok();
        }else {
            return R.error("注册失败");
        }
    }
}
