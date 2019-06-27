package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.friendsUtilsMapper;
import com.gabe.mychat.pojo.friends;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.service.FriendService;
import com.gabe.mychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    friendsUtilsMapper friendsUtilsMapper;
    @Autowired
    com.gabe.mychat.mapper.userMapper userMapper;
    @Autowired
    UserService UserService;
    @Override
  /*  @Cacheable(value ="selectFriendById")*/
    public  List<friends> selectFriendById(String userid) {
        List<friends> list=friendsUtilsMapper.selectByUserId(userid);
        return list;
    }

    @Override
/*    @Cacheable(value = "getFriendsInfo")*/
    public List<Map> getFriendsInfo(List<friends> list) {
        List<Map> list1=new ArrayList();
        Iterator it=list.iterator();
        while(it.hasNext()){
            friends friends=(friends) it.next();
            Map map=new HashMap();
            String friendid=friends.getFriendId();
            map.put("userId",friendid);
            user user=userMapper.selectByPrimaryKey(friendid);
            map.put("nickname",user.getNickname());
            map.put("imgurl",user.getImgurl());
            list1.add(map);
        }
        return list1;
    }

}
