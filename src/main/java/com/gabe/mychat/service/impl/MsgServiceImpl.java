package com.gabe.mychat.service.impl;

import com.gabe.mychat.pojo.message;
import com.gabe.mychat.service.MsgService;
import com.gabe.mychat.util.QuickSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        // 分别查找A发给B，B发给A的所有信息
        List<message> activeList = messageUtilMapper.selectByTime(friendId, userId);
        List<message> passiveList = messageUtilMapper.selectByTime(userId, friendId);
        // 通过循环将每个message的status变为1
        for(message message : activeList){
            message.setStatus(1);
            messageMapper.updateByPrimaryKey(message);
        }
        for(message message : passiveList){
            message.setStatus(1);
            messageMapper.updateByPrimaryKey(message);
        }
        // 新建权值队列，重写比较方法

        message[] messages = new message[activeList.size() + passiveList.size()];
        int index = 0;
        for(message message : activeList){
            messages[index++] = message;
        }
        for(message message : passiveList){
            messages[index++] = message;
        }
        QuickSort.sort(messages, 0, messages.length - 1);
        // 将priorityqueue中的数据转入list，并截取前20条数据
        List<message> list = new ArrayList<>();

        if (messages.length > 20) {
            for (int i = messages.length-20; i < messages.length ; i++){
                list.add(messages[i]);
            }
        }else {
            list.addAll(Arrays.asList(messages));
        }
        return list;
    }
}
