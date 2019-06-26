package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.userUtilMapper;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    userUtilMapper userUtilMapper;
    @Autowired
    com.gabe.mychat.mapper.userMapper userMapper;

    @Override
    public List<user> selectUserByNickName(String nickname) {
        return userUtilMapper.selectUserByNickName(nickname);
    }

    @Override
    public user selectUserById(String userid) {
        return userMapper.selectByPrimaryKey(userid);
    }
}
