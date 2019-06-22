package com.gabe.mychat.pojo;

public class admin {
    private String userId;

    public admin(String userId) {
        this.userId = userId;
    }

    public admin() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}