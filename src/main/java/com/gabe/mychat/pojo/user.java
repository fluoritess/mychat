package com.gabe.mychat.pojo;

public class user {
    private String userId;

    private String name;

    private String nickname;

    private String imgurl;

    private String tel;

    private String password;

    private Integer role;

    public user(String userId, String name, String nickname, String imgurl, String tel, String password, Integer role) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.imgurl = imgurl;
        this.tel = tel;
        this.password = password;
        this.role = role;
    }

    public user() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}