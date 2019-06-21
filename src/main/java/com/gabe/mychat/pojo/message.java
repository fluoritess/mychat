package com.gabe.mychat.pojo;

public class message {
    private Integer messageId;

    private String content;

    private Integer messageType;

    private Integer receiverId;

    private Integer senderId;

    public message(Integer messageId, String content, Integer messageType, Integer receiverId, Integer senderId) {
        this.messageId = messageId;
        this.content = content;
        this.messageType = messageType;
        this.receiverId = receiverId;
        this.senderId = senderId;
    }

    public message() {
        super();
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }
}