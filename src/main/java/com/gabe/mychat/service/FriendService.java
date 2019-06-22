package com.gabe.mychat.service;

import com.gabe.mychat.pojo.user;

/**
 * @author wsw
 * @Package com.gabe.mychat.service
 * @Description:
 * @date 2019年6月22日 16:59:39
 */
public interface FriendService {
        //根据id查询好友
        user selectFriendById();
}
