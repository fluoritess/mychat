package com.gabe.mychat.pojo;

import java.util.Date;

public class friends {
    private String userId;

    private Integer friendId;

    private Date addTime;

    public friends(String userId, Integer friendId, Date addTime) {
        this.userId = userId;
        this.friendId = friendId;
        this.addTime = addTime;
    }

    public friends() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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