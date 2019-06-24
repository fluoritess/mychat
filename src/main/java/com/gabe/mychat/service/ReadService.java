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

    public Queue<LastMessage> selectUnreadMessage(String userid);
}
