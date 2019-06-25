package com.gabe.mychat.service.impl;

import com.gabe.mychat.pojo.message;
import com.gabe.mychat.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    com.gabe.mychat.mapper.messageMapper messageMapper;

    @Autowired
    com.gabe.mychat.mapper.messageUtilMapper messageUtilMapper;

    @Override
    public int addFriendMsg(message message) {
        return messageMapper.insert(message);
    }

    @Override
    public List<message> selectByTime(String userId, String friendId) {
        List<message> activeList = messageUtilMapper.selectByTime(friendId, userId);
        List<message> passiveList = messageUtilMapper.selectByTime(userId, friendId);

        PriorityQueue<message> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getSendDate().after(o2.getSendDate())) {
                return 1;
            } else if (o1.getSendDate().equals(o2.getSendDate())) {
                return 0;
            } else {
                return -1;
            }
        });
        priorityQueue.addAll(activeList);
        priorityQueue.addAll(passiveList);

        List<message> list = new ArrayList<>(priorityQueue);
        if (list.size() > 20) {
            list = list.subList(0, 20);
        }
        return list;
    }
}
