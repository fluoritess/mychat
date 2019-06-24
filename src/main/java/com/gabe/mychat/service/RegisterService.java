package com.gabe.mychat.service;

import com.gabe.mychat.pojo.user;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 上午 11:29
 * @since jdk
 */
public interface RegisterService {

    public String sendTelCode(String tel);

    public boolean checkRegister(String tel);

    public int userRegister(user user);
}
