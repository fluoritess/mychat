package com.gabe.mychat.service;

import com.gabe.mychat.pojo.normalUser;

/**
 * @author wsw
 * @Package com.gabe.mychat.service
 * @Description:
 * @date 2019年6月22日 17:03:50
 */
public interface NormalUserService {
    //通过id查询普通用户剩余信息
    normalUser selectNormalUserById(String userid);
}
