package com.gabe.mychat.pojo;

import java.util.Date;

public class broadcast {
    private Integer broadcastId;

    private String broadcastContent;

    private Date broadcastTime;

    public broadcast(Integer broadcastId, String broadcastContent, Date broadcastTime) {
        this.broadcastId = broadcastId;
        this.broadcastContent = broadcastContent;
        this.broadcastTime = broadcastTime;
    }

    public broadcast() {
        super();
    }

    public Integer getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(Integer broadcastId) {
        this.broadcastId = broadcastId;
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
}