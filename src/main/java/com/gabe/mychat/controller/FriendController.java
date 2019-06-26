package com.gabe.mychat.controller;

        import com.gabe.mychat.mapper.normalUserUtilMapper;
        import com.gabe.mychat.mapper.userUtilMapper;
import com.gabe.mychat.pojo.friends;
import com.gabe.mychat.pojo.normalUser;
        import com.gabe.mychat.pojo.user;
        import com.gabe.mychat.service.FriendService;
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
        import java.util.*;

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
    @Autowired
    com.gabe.mychat.mapper.userMapper userMapper;
    @Autowired
    normalUserUtilMapper normalUserUtilMapper;

    /**
     * 查询信息
     * @param reMap
     * @return
     */
    @ResponseBody
    @ArchivesLog(operationName = "查询全部好友信息",operationType = "查询操作")
    @RequestMapping("/selectFriendInfo")
    public R selectFriendInfo(HttpSession session){
        String id=(String)session.getAttribute("id");
        List<friends> list=friendService.selectFriendById(id);
        List<Map> list1=friendService.getFriendsInfo(list);
        return R.ok().put("data",list1);
    }
    /**
     * 查询信息
     * @param reMap
     * @return
     */
    @ResponseBody
    @ArchivesLog(operationName = "根据id或昵称查询好友信息",operationType = "查询操作")
    @RequestMapping("/selectFriendInfoById")
    public R selectFriendInfoById(@RequestBody Map<String, Object> reMap , HttpSession session){
        String id=(String)session.getAttribute("id");
        String nickname=(String)reMap.get("value");
        if(id.equals("")||id.equals(null)||nickname.equals(null)||nickname.equals("")){
            return R.error("无数据");
        }
        List list_=new ArrayList();
        //长度为12且不全为数字，则是昵称查询
        if(NumberUtil.getNumberFromString(nickname).length()!=12){
            //获取好友的id
            nickname=userUtilMapper.selectUserByNickName(nickname).getUserId();
            List<friends> list=friendService.selectFriendById(id);
            Iterator it=list.iterator();
            while(it.hasNext()){
                friends friends=(friends) it.next();
                String friendId=friends.getFriendId();
                if(friendId.equals(nickname)){
                    user user=userMapper.selectByPrimaryKey(nickname);
                    normalUser normalUser=normalUserUtilMapper.selectUserById(user.getUserId());
                    Map map=new HashMap();
                    map= UserUtil.completeUser(user,normalUser);
                    map.remove("gender");
                    map.remove("address");
                    map.remove("age");
                    list_.add(map);
                    return R.ok().put("data",list_);
                }
            }
        }
        //长度为12且为数字，则是id查询
        else {
            List<friends> list=friendService.selectFriendById(id);
            Iterator it=list.iterator();
            while(it.hasNext()){
                friends friends=(friends) it.next();
                String friendId=friends.getFriendId();
                if(friendId.equals(nickname)){
                    user user=userMapper.selectByPrimaryKey(nickname);
                    normalUser normalUser=normalUserUtilMapper.selectUserById(user.getUserId());
                    Map map=new HashMap();
                    map= UserUtil.completeUser(user,normalUser);
                    map.remove("gender");
                    map.remove("address");
                    map.remove("age");
                    list_.add(map);
                    return R.ok().put("data",list_);
                }
            }
        }
        return  R.error("无数据");
    }
}
