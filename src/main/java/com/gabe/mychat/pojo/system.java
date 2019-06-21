package com.gabe.mychat.pojo;

public class system {
    private Integer systemId;

    private String salt;

    public system(Integer systemId, String salt) {
        this.systemId = systemId;
        this.salt = salt;
    }

    public system() {
        super();
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}