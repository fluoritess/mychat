package com.gabe.mychat.util;

import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.user;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wsw
 * @Package com.gabe.mychat.util
 * @Description:用户工具类
 * @date 2019年6月22日 16:13:02
 */
public class UserUtil {
    //用户信息补全
    public static Map completeUser(user user, normalUser normalUser){
        Map map=new HashMap();
        map.put("userId",user.getUserId());
        map.put("nickname",user.getNickname());
        map.put("name",user.getName());
        map.put("imgurl",user.getImgurl());
        map.put("gender",normalUser.getGender());
        map.put("address",normalUser.getAddress());
        map.put("age",normalUser.getAge());
        return map;
    }
}
