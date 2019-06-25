package com.gabe.mychat.service;

import com.gabe.mychat.pojo.normalUser;
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

    /**
     * 发送手机验证码
     *
     * @param tel user's tel
     * @return String code
     */
    String sendTelCode(String tel);

    /**
     * 检查手机号是否被注册
     *
     * @param tel user's tel
     * @return boolean is the tel registered
     */
    boolean checkRegister(String tel);

    /**
     * 用户注册
     *
     * @param user       user
     * @param normalUser normalUser
     * @return boolean did register successfully
     */
    boolean userRegister(user user, normalUser normalUser);
}
