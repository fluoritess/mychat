package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.normalUserUtilMapper;
import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class NormalUserServiceImpl implements NormalUserService {
    @Autowired
    normalUserUtilMapper normalUserUtilMapper;

    @Override
    public normalUser selectNormalUserById(String userid) {
        return normalUserUtilMapper.selectUserById(userid);

    }
}
