package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.messageMapper;
import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 下午 18:39
 * @since jdk
 */
@Controller
public class ReadController {

    @Autowired
    messageMapper messageMapper;

    @ResponseBody
    @ArchivesLog(operationName = "获取手机验证码", operationType = "用户基本操作")
    @RequestMapping("/selectUnreadMessage")
    public R selectUnreadMessage(HttpSession session){
        String userid = (String) session.getAttribute("id");

        return null;
    }

}
