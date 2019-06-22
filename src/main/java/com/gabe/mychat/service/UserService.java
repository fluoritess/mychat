package com.gabe.mychat.service;

import com.gabe.mychat.pojo.user;

/**
 * @author wsw
 * @Package com.gabe.mychat.service
 * @Description:
 * @date 2019年6月21日 15:21:58
 */
public interface UserService {
    //根据昵称查询用户
    user selectUserByNickName(String nickname);
    //根据id查询用户
    user selectUserById(String userid);
}
