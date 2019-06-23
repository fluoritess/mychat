package com.gabe.mychat.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wsw
 * @Package com.gabe.mychat.util
 * @Description:JavaBean转为Map工具类
 * @date 2019年6月22日 16:13:02
 */
public class BeanUtil {
    public static Map<String, String> getValueMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            // 获取原来的访问控制权限
            boolean accessFlag = fields[i].isAccessible();
            // 修改访问控制权限
            fields[i].setAccessible(true);
            // 获取在对象f中属性fields[i]对应的对象中的变量
            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());
            // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
            // 恢复访问控制权限
            fields[i].setAccessible(accessFlag);
        }
        return map;
    }
}
