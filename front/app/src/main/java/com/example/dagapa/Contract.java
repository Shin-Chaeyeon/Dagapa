package com.example.dagapa;

import java.io.Serializable;

public class Contract implements  Serializable {

    String iamage;
    int contractno;
    String lender;
    String borrower;
    int type;
    String goods;
    String startdate;
    String duedate;
    String umageurl;
    String audiourl;
    String description;
    int agreed;
    int status;

    // 생성자
    public Contract(String goods, String duedate) {
        this.goods = goods;
        this.duedate = duedate;
    }

    public String getIamage() {
        return iamage;
    }

    public void setIamage(String iamage) {
        this.iamage = iamage;
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

    public String getUmageurl() {
        return umageurl;
    }

    public void setUmageurl(String umageurl) {
        this.umageurl = umageurl;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Contract{" +
                "iamage='" + iamage + '\'' +
                ", contractno=" + contractno +
                ", lender='" + lender + '\'' +
                ", borrower='" + borrower + '\'' +
                ", type=" + type +
                ", goods='" + goods + '\'' +
                ", startdate='" + startdate + '\'' +
                ", duedate='" + duedate + '\'' +
                ", umageurl='" + umageurl + '\'' +
                ", audiourl='" + audiourl + '\'' +
                ", description='" + description + '\'' +
                ", agreed=" + agreed +
                ", status=" + status +
                '}';
    }
}