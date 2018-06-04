package com.nguyenquanghuy605.bookyardfootball.Model;

public class BookYard {
    private long account;
    private String date;
    private long id;
    private long price;
    private long status;
    private long subyard;
    private long timestart;
    private long timeend;

    public BookYard(){}

    public BookYard(long account, String date, long id, long price, long status, long subyard, long timestart, long timeend) {
        this.account = account;
        this.date = date;
        this.id = id;
        this.price = price;
        this.status = status;
        this.subyard = subyard;
        this.timestart = timestart;
        this.timeend = timeend;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getSubyard() {
        return subyard;
    }

    public void setSubyard(long subyard) {
        this.subyard = subyard;
    }

    public long getTimestart() {
        return timestart;
    }

    public void setTimestart(long timestart) {
        this.timestart = timestart;
    }

    public long getTimeend() {
        return timeend;
    }

    public void setTimeend(long timeend) {
        this.timeend = timeend;
    }
}
