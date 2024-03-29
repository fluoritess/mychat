package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.friendsMapper;
import com.gabe.mychat.pojo.friends;
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
import java.util.List;
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
    @Autowired
    friendsMapper friendsMapper;
    @ResponseBody
    @ArchivesLog(operationName = "添加好友", operationType = "互动操作")
    @RequestMapping("/addfriend")
    public R addFriend(@RequestBody Map<String, Object> map, HttpSession session) {
        String user_id = (String) session.getAttribute("id");
        String friend_id = (String) map.get("adduserid");
        String msg = (String) map.get("msg");
        int message_type = 3;
        Date date = new Date();
        long date_time = date.getTime();
        /*  int date_time_int=(int)date_time;*/
        String message_id = date_time + user_id;
        int status = 0;
        message message = new message(message_id, msg, message_type, friend_id, user_id, status, date);
        try {
            //消息表中添加消息
            int i = msgService.addFriendMsg(message);
            if (i != 1) {
                return R.error("消息发送失败");
            }
            //好友表中添加好友
            friends friend=new friends(user_id,friend_id,date);
            friends friend1=new friends(friend_id,user_id,date);
            friendsMapper.insert(friend);
            friendsMapper.insert(friend1);
            return R.ok("消息发送成功");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }


    }

    /**
     * 查询当前用户与选择好友的最近20条记录
     *
     * @param map     json
     * @param session session
     * @return R
     */
    @ResponseBody
    @ArchivesLog(operationName = "查询当前用户与选择好友的最近20条记录", operationType = "查询操作")
    @RequestMapping("/selectByTime")
    public R selectByTime(@RequestBody Map<String, Object> map, HttpSession session) {
        String userId = (String) session.getAttribute("id");
        String friendId = (String) map.get("userid");

        List<message> list = msgService.selectByTime(userId, friendId);
        if(list != null){
            return R.ok().put("data", list);
        }else {
            return R.error("查询记录失败");
        }

    }
}
