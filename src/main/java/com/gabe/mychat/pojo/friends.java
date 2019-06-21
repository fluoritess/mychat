package com.gabe.mychat.pojo;

import java.util.Date;

public class friends {
    private Integer userId;

    private Integer friendId;

    private Date addTime;

    public friends(Integer userId, Integer friendId, Date addTime) {
        this.userId = userId;
        this.friendId = friendId;
        this.addTime = addTime;
    }

    public friends() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}