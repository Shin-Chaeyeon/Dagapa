package com.example.dagapa;

import java.io.Serializable;

public class Contract implements  Serializable {

    String image;
    int contractno;
    String lender;
    String borrower;
    int type;
    String goods;
    String startdate;
    String duedate;
    String imageurl;
    String description;
    int status;

    // 생성자
    public Contract(String goods, String duedate) {
        this.goods = goods;
        this.duedate = duedate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "image='" + image + '\'' +
                ", contractno=" + contractno +
                ", lender='" + lender + '\'' +
                ", borrower='" + borrower + '\'' +
                ", type=" + type +
                ", goods='" + goods + '\'' +
                ", startdate='" + startdate + '\'' +
                ", duedate='" + duedate + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}