package com.gabe.mychat.pojo;

public class admin {
    private Integer userId;

    public admin(Integer userId) {
        this.userId = userId;
    }

    public admin() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}