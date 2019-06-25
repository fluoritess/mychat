package com.gabe.mychat.service.impl;

import com.gabe.mychat.pojo.message;
import com.gabe.mychat.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * description:
 *
 * @author wsw
 * @version 1.0
 * @date 2019/6/25 0025 下午 16:39
 * @since jdk
 */
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

    /**
     * 查询最近发送的20条消息
     * @param userId user's id
     * @param friendId friend's id
     * @return List<message> the list of message which find
     */
    @Override
    public List<message> selectByTime(String userId, String friendId) {
        // 分别查找A发给B，B发给A的各前20条信息
        List<message> activeList = messageUtilMapper.selectByTime(friendId, userId);
        List<message> passiveList = messageUtilMapper.selectByTime(userId, friendId);
        // 新建权值队列，重写比较方法
        PriorityQueue<message> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getSendDate().after(o2.getSendDate())) {
                return 1;
            } else if (o1.getSendDate().equals(o2.getSendDate())) {
                return 0;
            } else {
                return -1;
            }
        });
        // 将查找到的数据全部压入权值队列，此时队列的size最大为40
        priorityQueue.addAll(activeList);
        priorityQueue.addAll(passiveList);
        // 将priorityqueue中的数据转入list，并截取前20条数据
        List<message> list = new ArrayList<>(priorityQueue);
        if (list.size() > 20) {
            list = list.subList(0, 20);
        }
        return list;
    }
}
