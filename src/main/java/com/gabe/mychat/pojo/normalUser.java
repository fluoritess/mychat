package com.gabe.mychat.pojo;

public class normalUser {
    private String userId;

    private String gender;

    private String address;

    private Integer age;

    public normalUser(String userId, String gender, String address, Integer age) {
        this.userId = userId;
        this.gender = gender;
        this.address = address;
        this.age = age;
    }

    public normalUser() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}