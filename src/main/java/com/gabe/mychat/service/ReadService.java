package com.gabe.mychat.service;

import com.gabe.mychat.util.LastMessage;

import java.util.Queue;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 下午 19:29
 * @since jdk
 */
public interface ReadService {

    /**
     * 选择用户未读信息
     *
     * @param userid user's id
     * @return Queue<LastMessage> the queue of the information about the senders and what he send
     */
    public Queue<LastMessage> selectUnreadMessage(String userid);
}
