package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.mapper.userUtilMapper;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    userUtilMapper userUtilMapper;
    @Autowired
    userMapper userMapper;
    @Override
    public user selectUserByNickName(String nickname) {
        return userUtilMapper.selectUserByNickName(nickname);
    }

    @Override
    public user selectUserById(String userid) {
        return userMapper.selectByPrimaryKey(userid);
    }
}
