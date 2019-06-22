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
import com.gabe.mychat.util.ArchivesLog;
import com.gabe.mychat.util.NumberUtil;
import com.gabe.mychat.util.R;
import com.gabe.mychat.util.UserUtil;
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
    @ArchivesLog(operationName = "查询好友信息",operationType = "查询操作")
    @RequestMapping("/selectFriendInfo")
    public R addFriend(@RequestBody Map<String, Object> reMap , HttpSession session){
         String nickname=(String) reMap.get("nickname");
        //长度为12且不全为数字，则是昵称查询
        if(NumberUtil.getNumberFromString(nickname).length()!=12){
            user user= userUtilMapper.selectUserByNickName(nickname);
            String user_id=user.getUserId();
            List<friends> list=friendService.selectFriendById(user_id);
            List<Map> list1=friendService.getFriendsInfo(list);
            return R.ok().put("data",list1);
        }
        //长度为12且为数字，则是id查询
        else {
            List<friends> list=friendService.selectFriendById(nickname);
            List<Map> list1=friendService.getFriendsInfo(list);
            return R.ok().put("data",list1);
        }

    }
}
