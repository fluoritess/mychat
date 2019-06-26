package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.messageMapper;
import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.pojo.message;
import com.gabe.mychat.pojo.messageExample;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.service.ReadService;
import com.gabe.mychat.util.LastMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 下午 19:31
 * @since jdk
 */
@Service
public class ReadServiceImpl implements ReadService {

    @Autowired
    private com.gabe.mychat.mapper.userMapper userMapper;

    @Autowired
    private messageMapper messageMapper;

    /**
     * 选择用户未查看的最后一条信息及发送人的信息，及该发送人发送的未读信息总数
     *
     * @param userId userId
     * @return Queue<LastMessage> queue of the information who send the message and other something
     */
    @Override
    public Queue<LastMessage> selectUnreadMessage(String userId) {
        // 查询该用户的所有未读信息
        messageExample messageExample = new messageExample();
        com.gabe.mychat.pojo.messageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andReceiverIdEqualTo(userId).andStatusEqualTo(0);
        List<message> messageList = messageMapper.selectByExample(messageExample);
        // 开始遍历所有的未读信息
        Queue<LastMessage> messageQueue = new LinkedList<>();
        // 信息在数据库中是按时间的递增顺序存储，因此倒序查看
        for (int i = messageList.size(); i > 0; i--) {
            message message = messageList.get(i - 1);
            // 一个标记，如果遇到了相同联系人发送的信息则跳出循环
            boolean flag = false;
            if (!messageQueue.isEmpty()) {
                // 检查在消息队列中是否有相同联系人发送的信息
                for (LastMessage lastMessage : messageQueue) {
                    if (lastMessage.getUserId().equals(message.getSenderId())) {
                        // 如果有相同联系人发送的信息，则在其发送的数量上+1
                        int number = Integer.parseInt(lastMessage.getNumber());
                        lastMessage.setNumber(String.valueOf(number + 1));
                        flag = true;
                        break;
                    }
                }
                // 在遇到了相同联系人发送的信息时跳出循环
                if (flag) {
                    continue;
                }
            }
            // 在没遇到相同联系人发送的信息时，获取发送人的信息
            user user = userMapper.selectByPrimaryKey(message.getSenderId());
            // 并将消息压入消息队列
            LastMessage lastMessage = new LastMessage(user.getUserId(), user.getNickname(), user.getImgurl(),
                    message.getContent(), message.getSendDate(), "1");
            messageQueue.offer(lastMessage);

        }

        return messageQueue;
    }

}
