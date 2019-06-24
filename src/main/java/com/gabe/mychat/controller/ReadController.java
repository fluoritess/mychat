package com.gabe.mychat.controller;

import com.gabe.mychat.service.ReadService;
import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.LastMessage;
import com.gabe.mychat.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Queue;

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
    ReadService readService;

    @ResponseBody
    @ArchivesLog(operationName = "查询好友发送未读取信息", operationType = "用户基本操作")
    @RequestMapping("/readUnreadMessage")
    public R selectUnreadMessage(HttpSession session) {
//        String userId = (String) session.getAttribute("id");
        String userId = "123456789102";
        Queue<LastMessage> messageQueue = readService.selectUnreadMessage(userId);
        return R.ok().put("data", messageQueue);
    }

}
