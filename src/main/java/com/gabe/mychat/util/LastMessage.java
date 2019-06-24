package com.gabe.mychat.util;

import java.util.Date;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/24 0024 下午 19:49
 * @since jdk
 */
public class LastMessage {
    //发送信息的好友的id，昵称，头像，最后一条信息的数据，时间，未查阅信息的总条数

    private String userId;
    private String nickname;
    private String imgurl;
    private String content;
    private Date date;
    private String number;

    public LastMessage(String userId, String nickname, String imgurl, String content, Date date, String number) {
        this.userId = userId;
        this.nickname = nickname;
        this.imgurl = imgurl;
        this.content = content;
        this.date = date;
        this.number = number;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
