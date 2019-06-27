package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.normalUserMapper;
import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.normalUserExample;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.pojo.userExample;
import com.gabe.mychat.service.AdminService;
import com.gabe.mychat.util.PerfectUser;
import com.gabe.mychat.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    RedisUtil redisUtil;
    @Override
    /*@Cacheable(value ="findAllUser")*/
    public List<PerfectUser> findAllUser(int status) {
        List<PerfectUser> perfectUserList=null;
        //从缓存中拿取
        List quarterlist=redisUtil.lRange("quarterlist",0,-1);
        if(quarterlist.size()==0){
            userExample userExample = new userExample();
            if (status != 2) {
                userExample.createCriteria().andStatusEqualTo(status);
            }
            List<user> userList = userMapper.selectByExample(userExample);
            normalUserExample normalUserExample = new normalUserExample();
            List<normalUser> normalUserList = normalUserMapper.selectByExample(normalUserExample);
            perfectUserList = new ArrayList<>();
            for (user user : userList) {
                for (normalUser normalUser : normalUserList) {
                    if (normalUser.getUserId().equals(user.getUserId())) {
                        perfectUserList.add(new PerfectUser(user, normalUser));
                        break;
                    }
                }
            }
            redisUtil.lSetlist("quarterlist",perfectUserList,1);
        }
        else{
            return quarterlist;
        }

        return perfectUserList;
    }

    @Override
    public int getUserNumber() {
        userExample userExample = new userExample();
        return userMapper.selectByExample(userExample).size();
    }

    @Override
    public List<Map<String, String>> getUserAddress() {
        normalUserExample normalUserExample = new normalUserExample();
        List<normalUser> normalUserList = normalUserMapper.selectByExample(normalUserExample);
        List<Map<String, String>> list = new ArrayList<>();
        for (normalUser normalUser : normalUserList) {
            String address = normalUser.getAddress();
            address = address == null ? "null" : address.split("-")[0];

            boolean flag = false;
            for (Map<String, String> map : list) {
                if (map.get("name").equals(address)) {
                    map.put("number", String.valueOf(Integer.parseInt(map.get("number")) + 1));
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            Map<String, String> map = new HashMap<>();
            map.put("name", address);
            map.put("number", String.valueOf(1));
            list.add(map);
        }

        return list;
    }

    @Override
    public List<Map<String, String>> getUserGender() {
        normalUserExample normalUserExample = new normalUserExample();
        List<normalUser> normalUserList = normalUserMapper.selectByExample(normalUserExample);
        List<Map<String, String>> list = new ArrayList<>();

        for (normalUser normalUser : normalUserList) {
            String gender = "null";
            if (normalUser.getGender() != null) {
                gender = normalUser.getGender();
            }
            if("null".equals(gender)){
                continue;
            }

            boolean flag = false;
            for (Map<String, String> map : list) {
                if (map.get("name").equals(gender)) {
                    map.put("number", String.valueOf(Integer.parseInt(map.get("number")) + 1));
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            Map<String, String> map = new HashMap<>();
            map.put("name", gender);
            map.put("number", String.valueOf(1));
            list.add(map);
        }
        return list;
    }

    @Override
   /* @CachePut(value ="findAllUser")*/
    public boolean prohibitUser(String userId) {
        List quarterlist=redisUtil.lRange("quarterlist",0,-1);

        user user = userMapper.selectByPrimaryKey(userId);
        user.setStatus(1);
        for(int i=0;i<quarterlist.size();i++){
            PerfectUser PerfectUser=(PerfectUser)quarterlist.get(i);
            if(PerfectUser.getUserId().equals(user.getUserId())){
                PerfectUser.setStatus(1);
            }
        }
        redisUtil.lSetlist("quarterlist",quarterlist,1);
        return userMapper.updateByPrimaryKey(user) != 0;
    }

    @Override
   /* @CachePut(value ="findAllUser")*/
    public boolean releaseUser(String userId) {
        List quarterlist=redisUtil.lRange("quarterlist",0,-1);

        user user = userMapper.selectByPrimaryKey(userId);
        user.setStatus(0);
        for(int i=0;i<quarterlist.size();i++){
            PerfectUser PerfectUser=(PerfectUser)quarterlist.get(i);
            if(PerfectUser.getUserId().equals(user.getUserId())){
                PerfectUser.setStatus(1);
            }
        }
        redisUtil.lSetlist("quarterlist",quarterlist,0);
        return userMapper.updateByPrimaryKey(user) != 0;
    }
}
