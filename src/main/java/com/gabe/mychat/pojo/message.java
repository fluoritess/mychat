package com.gabe.mychat.pojo;

public class message {
    private String messageId;

    private String content;

    private Integer messageType;

    private String receiverId;

    private String senderId;

    public message(String messageId, String content, Integer messageType, String receiverId, String senderId) {
        this.messageId = messageId;
        this.content = content;
        this.messageType = messageType;
        this.receiverId = receiverId;
        this.senderId = senderId;
    }

    public message() {
        super();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
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

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }
}