package com.example.dagapa;

import java.io.Serializable;

public class Contract implements  Serializable {
//public class Contract implements Serializable {

    int contractno;
    String lender;
    String borrower;
    int type;
    String goods;
    String duedate;
    String picture;
    String record;
    String description;
    int agreed;

    //생성자 테스트


    public Contract(String goods, String duedate) {
        this.goods = goods;
        this.duedate = duedate;
    }

    public int getContractno() {
        return contractno;
    }

    public void setContractno(int contractno) {
        this.contractno = contractno;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgreed() {
        return agreed;
    }

    public void setAgreed(int agreed) {
        this.agreed = agreed;
    }

//
//    String name;
//    String mobile;
//
//    public Contract(String name, String mobile){
//        this.name = name;
//        this.mobile = mobile;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }


}


