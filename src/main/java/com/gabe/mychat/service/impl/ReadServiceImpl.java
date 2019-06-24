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
    private userMapper userMapper;

    @Autowired
    private messageMapper messageMapper;

    @Override
    public Queue<LastMessage> selectUnreadMessage(String userid) {
        messageExample messageExample = new messageExample();
        com.gabe.mychat.pojo.messageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andReceiverIdEqualTo(userid);
        List<message> messageList = messageMapper.selectByExample(messageExample);

        Queue<LastMessage> messageQueue = new LinkedList<>();
        for (int i = messageList.size(); i > 0; i--) {
            message message = messageList.get(i - 1);

            boolean flag = false;
            if (!messageQueue.isEmpty()) {
                for (LastMessage lastMessage : messageQueue) {
                    if (lastMessage.getUserId().equals(message.getSenderId())) {
                        int number = Integer.parseInt(lastMessage.getNumber());
                        lastMessage.setNumber(String.valueOf(number + 1));
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    continue;
                }
            }

            user user = userMapper.selectByPrimaryKey(message.getSenderId());
            LastMessage lastMessage = new LastMessage(user.getUserId(), user.getNickname(), user.getImgurl(),
                    message.getContent(), message.getSendDate(), "1");
            messageQueue.offer(lastMessage);

        }

        return messageQueue;
    }

}
