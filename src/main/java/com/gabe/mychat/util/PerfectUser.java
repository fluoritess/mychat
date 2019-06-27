package com.gabe.mychat.util;

import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.user;

/**
 * description:
 *
 * @author haifeng
 * @version 1.0
 * @date 2019/6/26 0026 上午 11:40
 * @since jdk
 */
public class PerfectUser {

    private String userId;
    private String name;
    private String nickname;
    private String imgurl;
    private String tel;
    private String password;
    private String gender;
    private String address;
    private Integer age;
    private Integer status;

    public PerfectUser(user user, normalUser normalUser) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.imgurl = user.getImgurl();
        this.tel = user.getTel();
        this.password = user.getPassword();
        this.gender = normalUser.getGender();
        this.address = normalUser.getAddress();
        this.age = normalUser.getAge();
        this.status = user.getStatus();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
