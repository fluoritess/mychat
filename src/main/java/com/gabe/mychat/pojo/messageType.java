package com.gabe.mychat.pojo;

public class messageType {
    private Integer messagaeTypeId;

    private String messageType;

    public messageType(Integer messagaeTypeId, String messageType) {
        this.messagaeTypeId = messagaeTypeId;
        this.messageType = messageType;
    }

    public messageType() {
        super();
    }

    public Integer getMessagaeTypeId() {
        return messagaeTypeId;
    }

    public void setMessagaeTypeId(Integer messagaeTypeId) {
        this.messagaeTypeId = messagaeTypeId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }
}