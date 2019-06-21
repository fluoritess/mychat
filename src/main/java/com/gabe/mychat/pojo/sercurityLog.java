package com.gabe.mychat.pojo;

import java.util.Date;

public class sercurityLog {
    private Integer logId;

    private Integer userId;

    private Date loginTime;

    private String loginAddress;

    public sercurityLog(Integer logId, Integer userId, Date loginTime, String loginAddress) {
        this.logId = logId;
        this.userId = userId;
        this.loginTime = loginTime;
        this.loginAddress = loginAddress;
    }

    public sercurityLog() {
        super();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress == null ? null : loginAddress.trim();
    }
}