package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.normalUserMapper;
import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.normalUserExample;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.pojo.userExample;
import com.gabe.mychat.service.AdminService;
import com.gabe.mychat.util.Gender;
import com.gabe.mychat.util.PerfectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/26 0026 上午 10:49
 * @since jdk
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    com.gabe.mychat.mapper.userMapper userMapper;

    @Autowired
    normalUserMapper normalUserMapper;

    @Override
    public List<PerfectUser> findAllUser() {
        userExample userExample = new userExample();
        List<user> userList = userMapper.selectByExample(userExample);
        normalUserExample normalUserExample = new normalUserExample();
        List<normalUser> normalUserList = normalUserMapper.selectByExample(normalUserExample);
        List<PerfectUser> perfectUserList = new ArrayList<>();
        for (user user : userList) {
            for (normalUser normalUser : normalUserList) {
                if (normalUser.getUserId().equals(user.getUserId())) {
                    perfectUserList.add(new PerfectUser(user, normalUser));
                    break;
                }
            }
        }
        return perfectUserList;
    }

    @Override
    public int getUserNumber() {
        userExample userExample = new userExample();
        return userMapper.selectByExample(userExample).size();
    }

    @Override
    public Map<String, Integer> getUserAddress() {
        normalUserExample normalUserExample = new normalUserExample();
        List<normalUser> normalUserList = normalUserMapper.selectByExample(normalUserExample);
        Map<String, Integer> map = new HashMap<>();
        for (normalUser normalUser : normalUserList) {
            String address = normalUser.getAddress();
            address = address == null ? "null" : address.split("-")[0];
            if (map.containsKey(address)) {
                map.put(address, map.get(address) + 1);
            } else {
                map.put(address, 1);
            }
        }
        return map;
    }

    @Override
    public Map<Gender, Integer> getUserGender() {
        normalUserExample normalUserExample = new normalUserExample();
        List<normalUser> normalUserList = normalUserMapper.selectByExample(normalUserExample);
        Map<Gender, Integer> map = new HashMap<>();
        for (normalUser normalUser : normalUserList) {
            Gender gender = Gender.NULL;
            if(normalUser.getGender() != null){
                switch (normalUser.getGender()) {
                    case "男":
                        gender = Gender.Man;
                        break;
                    case "女":
                        gender = Gender.WOMAN;
                        break;
                    default:
                        break;
                }
            }
            if (map.containsKey(gender)) {
                map.put(gender, map.get(gender) + 1);
            } else {
                map.put(gender, 1);
            }
        }
        return map;
    }

    @Override
    public boolean prohibitUser(String userId) {
        user user = userMapper.selectByPrimaryKey(userId);
        user.setStatus(1);
        return userMapper.updateByPrimaryKey(user) != 0;
    }
}
