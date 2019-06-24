package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.pojo.userExample;
import com.gabe.mychat.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 上午 11:45
 * @since jdk
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private userMapper userMapper;

    @Override
    public boolean checkRegister(String tel) {
        userExample userExample = new userExample();
        com.gabe.mychat.pojo.userExample.Criteria criteria = userExample.createCriteria();
        criteria.andTelEqualTo(tel);
        return userMapper.selectByExample(userExample) != null;
    }

    @Override
    public int userRegister(user user) {
        Date date = new Date();
        String userid = String.valueOf(date.getTime()).substring(0, 12);
        user.setUserId(userid);
        return userMapper.insertSelective(user);
    }
}
