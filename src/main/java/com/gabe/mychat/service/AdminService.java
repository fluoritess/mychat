package com.gabe.mychat.service;

import com.gabe.mychat.util.Gender;
import com.gabe.mychat.util.PerfectUser;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/26 0026 上午 10:48
 * @since jdk
 */
public interface AdminService {

    List<PerfectUser> findAllUser(int status);

    int getUserNumber();

    List<Map<String, String>> getUserAddress();

    Map<Gender, Integer> getUserGender();

    boolean prohibitUser(String userId);

    boolean releaseUser(String userId);
}
