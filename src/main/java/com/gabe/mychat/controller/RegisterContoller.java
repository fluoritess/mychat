package com.gabe.mychat.controller;

import com.gabe.mychat.pojo.normalUser;
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
            // 判断手机号长度是否正确
            return R.error("手机号格式不正确");
        } else {
            for (char c : tel.toCharArray()) {
                // 判断手机号是否全为数字
                if (!Character.isDigit(c)) {
                    return R.error("手机号格式不正确");
                }
            }
        }
        if (!registerService.checkRegister(tel)) {
            // 检验手机号是否已被注册
            return R.error("该手机号已被注册");
        }
        // 发送手机验证码
        String code = registerService.sendTelCode(tel);
        if (code != null) {
            // 如果顺利发送手机验证码，则将验证码存在Shiro中
            ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            return R.ok();
        } else {
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
        // 将所有的字段取出来
        String tel = map.get("tel");
        String code = map.get("code");
        String username = map.get("username");
        String pass = map.get("pass");
        String password = map.get("password");
        // 从Shiro里面取出之前存的手机验证码
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null || !kaptcha.equalsIgnoreCase(code)) {
            return R.error("手机验证码不正确");
        }
        // 判断手机号格式是否正确
        if (tel.length() != 11) {
            return R.error("手机号格式不正确");
        } else {
            for (char c : tel.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return R.error("手机号格式不正确");
                }
            }
        }
        // 判断两次输入密码是否相同
        if (!pass.equals(password)) {
            return R.error("两次输入密码不同");
        }
        // 判断手机号是否已被注册
        if (!registerService.checkRegister(tel)) {
            return R.error("该手机号已被注册");
        }
        // 注册需要有两个表
        // user and normalUser
        boolean res = registerService.userRegister(
                new user(null, null, username, null, tel, password,0),
                new normalUser(null, null, null, null));
        // 根据返回值控制返回结果
        if (res) {
            return R.ok();
        } else {
            return R.error("注册失败");
        }
    }
}
