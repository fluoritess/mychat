package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.userMapper;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.pojo.userExample;
import com.gabe.mychat.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 上午 11:45
 * @since jdk
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private userMapper userMapper;

    @Override
    public String sendTelCode(String tel) {
        /*HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader(
                "Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");*/
        //头文件中转码

        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(Math.round(Math.random() * 10));
        }
        code.substring(0, 6);
        System.out.println("手机验证码为：" + code);

        /*NameValuePair[] data = {
                new NameValuePair("Uid", "phf449540929"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob", tel),
                new NameValuePair("smsText", "欢迎注册MyChat网络聊天平台，您的短信验证码为：" + code)};
        post.setRequestBody(data);

        try {
            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:" + statusCode);
            for (Header h : headers) {
                System.out.println(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
            System.out.println("平台返回信息为：" + result);
            //打印返回消息状态
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发送信息失败");
            return null;
        }

        post.releaseConnection();*/
        return code.toString();
    }

    @Override
    public boolean checkRegister(String tel) {
        userExample userExample = new userExample();
        com.gabe.mychat.pojo.userExample.Criteria criteria = userExample.createCriteria();
        criteria.andTelEqualTo(tel);
        List<user> list = userMapper.selectByExample(userExample);
        return list == null || list.size() == 0;
    }

    @Override
    public int userRegister(user user) {
        Date date = new Date();
        String userid = String.valueOf(date.getTime()).substring(0, 12);
        user.setUserId(userid);
        return userMapper.insertSelective(user);
    }
}
