package com.gabe.mychat.pojo;

public class normalUser {
    private Integer userId;

    private String gender;

    private String address;

    public normalUser(Integer userId, String gender, String address) {
        this.userId = userId;
        this.gender = gender;
        this.address = address;
    }

    public normalUser() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}