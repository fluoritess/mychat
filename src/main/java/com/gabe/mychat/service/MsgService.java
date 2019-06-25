package com.gabe.mychat.service;

import com.gabe.mychat.pojo.message;

import java.util.List;

/**
 * @author wsw
 * @Package com.gabe.mychat.service
 * @Description:
 * @date 2019年6月21日 23:44:40
 */
public interface MsgService {
    //添加好友验证信息
    int addFriendMsg(message message);

    List<message> selectByTime(String userId, String friendId);

}
