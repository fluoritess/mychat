package com.gabe.mychat.service.impl;

import com.gabe.mychat.pojo.message;
import com.gabe.mychat.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    com.gabe.mychat.mapper.messageMapper messageMapper;
    @Override
    public int addFriendMsg(message message) {
        return messageMapper.insert(message);
    }
}
