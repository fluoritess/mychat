package com.gabe.mychat.controller;

import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.R;
import com.gabe.mychat.util.ShiroUtils;
import com.google.code.kaptcha.Constants;
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
        if(tel.length() != 11){
            return R.error("电话号码格式不正确");
        }else{
            for(char c : tel.toCharArray()){
                if(!Character.isDigit(c)){
                    return R.error("电话号码格式不正确");
                }
            }
        }

        Map<String, Object> msg = new HashMap<>();
        msg.put("code", "0");
        return R.ok().put("data", msg);
    }
}
