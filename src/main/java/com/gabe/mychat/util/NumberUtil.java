package com.gabe.mychat.util;

import java.util.Scanner;
/**
 * @author wsw
 * @Package com.gabe.mychat.util
 * @Description:数字工具类
 * @date 2019年6月22日 16:13:02
 */
public class NumberUtil {
    //判断long类型数字长度
    public static int getNumberLenth(long num) {
        return (new Long(num)).toString().length();
    }
    //从字符串中提取数字
    public static String getNumberFromString(String num) {
        num = num.trim();
        String num2 = "";
        if (num != null && !"".equals(num)) {
            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(i) >= 48 && num.charAt(i) <= 57) {
                    num2 += num.charAt(i);
                }
            }
        }
        return num2;
    }
}