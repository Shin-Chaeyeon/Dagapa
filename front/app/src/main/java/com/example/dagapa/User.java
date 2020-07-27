package com.example.dagapa;

import java.io.Serializable;

public class User  implements Serializable {

    String userno;
    String id;
    String pw;
    String nickname;
    String phone;
    String lent;
    String borrowed;


    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLent() {
        return lent;
    }

    public void setLent(String lent) {
        this.lent = lent;
    }

    public String getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(String borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userno='" + userno + '\'' +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", lent='" + lent + '\'' +
                ", borrowed='" + borrowed + '\'' +
                '}';
    }
}
