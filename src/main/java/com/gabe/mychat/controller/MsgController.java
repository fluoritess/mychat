package com.gabe.mychat.controller;

import com.gabe.mychat.pojo.message;
import com.gabe.mychat.service.MsgService;
import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author wsw
 * @Package com.gabe.mychat.controller
 * @Description:消息Controller
 * @date 2019年6月21日 23:32:40
 */
@Controller
public class MsgController {
    @Autowired
    MsgService msgService;
    @ResponseBody
    @ArchivesLog(operationName = "添加好友",operationType = "互动操作")
    @RequestMapping("/addfriend")
    public R addFriend(@RequestBody Map<String, Object> map , HttpSession session){
        String user_id=(String)session.getAttribute("id");
        String friend_id=(String)map.get("adduserid");
        String msg=(String)map.get("msg");
        int message_type=3;
        Date date=new Date();
        long date_time=date.getTime();
        int date_time_int=(int)date_time;
        String message_id=date_time_int+ user_id;
        message message=new message(message_id,msg,message_type,friend_id,user_id);
        int i=msgService.addFriendMsg(message);
        if(i!=1){
            return R.error("消息发送失败");
        }
        return R.ok("消息发送成功");
    }

}