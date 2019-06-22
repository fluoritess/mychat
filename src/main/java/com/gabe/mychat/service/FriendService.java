package com.gabe.mychat.service;

import com.gabe.mychat.pojo.friends;
import com.gabe.mychat.pojo.user;

import java.util.List;
import java.util.Map;

/**
 * @author wsw
 * @Package com.gabe.mychat.service
 * @Description:
 * @date 2019年6月22日 16:59:39
 */
public interface FriendService {
        //根据id查询好友
        List<friends> selectFriendById(String userid);
        //获取到好友的id，昵称，头像
        List<Map> getFriendsInfo( List<friends> list);
}
