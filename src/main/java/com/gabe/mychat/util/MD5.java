package com.gabe.mychat.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Administrator
 * MD5信息加密,简称信息摘要
 * 最后得到32位的字符串
 */
public class MD5{

    /**
     * 对字符串md5加密(小写+数字)
     * @param content 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public String getMD5Lower(String content){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串.
            byte[] bt=md.digest();
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示.
            BigInteger bit=new BigInteger(1,bt);
            return bit.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对字符串md5加密(大写+数字)
     * @param content 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public  String getMD5Upper(String content) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = content.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
