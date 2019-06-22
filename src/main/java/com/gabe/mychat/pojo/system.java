package com.gabe.mychat.pojo;

public class system {
    private String systemId;

    private String salt;

    public system(String systemId, String salt) {
        this.systemId = systemId;
        this.salt = salt;
    }

    public system() {
        super();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}