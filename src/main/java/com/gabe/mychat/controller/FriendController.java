package com.gabe.mychat.controller;

import com.gabe.mychat.mapper.normalUserUtilMapper;
import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.mapper.userUtilMapper;
import com.gabe.mychat.pojo.friends;
import com.gabe.mychat.pojo.message;
import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.service.FriendService;
import com.gabe.mychat.service.MsgService;
import com.gabe.mychat.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wsw
 * @Package com.gabe.mychat.controller
 * @Description:
 * @date 2019年6月22日 16:49:28
 */
@Controller
public class FriendController {
    @Autowired
    FriendService friendService;
    @Autowired
    userUtilMapper userUtilMapper;
    /**
     * 查询信息
     * @param reMap
     * @return
     */
    @ResponseBody
    @ArchivesLog(operationName = "查询全部好友信息",operationType = "查询操作")
    @RequestMapping("/selectFriendInfo")
    public R addFriend(@RequestBody Map<String, Object> reMap , HttpSession session){
         String id=(String)session.getAttribute("id");
         List<friends> list=friendService.selectFriendById(id);
         List<Map> list1=friendService.getFriendsInfo(list);
         return R.ok().put("data",list1);
    }
}
