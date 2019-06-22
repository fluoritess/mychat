package com.gabe.mychat.pojo;

import java.util.Date;

public class broadcast {
    private String broadcastId;

    private String broadcastContent;

    private Date broadcastTime;

    private String userId;

    public broadcast(String broadcastId, String broadcastContent, Date broadcastTime, String userId) {
        this.broadcastId = broadcastId;
        this.broadcastContent = broadcastContent;
        this.broadcastTime = broadcastTime;
        this.userId = userId;
    }

    public broadcast() {
        super();
    }

    public String getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(String broadcastId) {
        this.broadcastId = broadcastId == null ? null : broadcastId.trim();
    }

    public String getBroadcastContent() {
        return broadcastContent;
    }

    public void setBroadcastContent(String broadcastContent) {
        this.broadcastContent = broadcastContent == null ? null : broadcastContent.trim();
    }

    public Date getBroadcastTime() {
        return broadcastTime;
    }

    public void setBroadcastTime(Date broadcastTime) {
        this.broadcastTime = broadcastTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}