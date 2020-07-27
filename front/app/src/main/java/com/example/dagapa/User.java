package com.example.dagapa;

import java.io.Serializable;

public class User  implements Serializable {

    String id;
    String pw;
    String name;
    String phone;
    String lent;
    String borrowed;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", lent='" + lent + '\'' +
                ", borrowed='" + borrowed + '\'' +
                '}';
    }
}
