package com.gabe.mychat.pojo;

import java.util.Date;

public class sercurityLog {
    private String logId;

    private String userId;

    private Date loginTime;

    private String loginAddress;

    public sercurityLog(String logId, String userId, Date loginTime, String loginAddress) {
        this.logId = logId;
        this.userId = userId;
        this.loginTime = loginTime;
        this.loginAddress = loginAddress;
    }

    public sercurityLog() {
        super();
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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