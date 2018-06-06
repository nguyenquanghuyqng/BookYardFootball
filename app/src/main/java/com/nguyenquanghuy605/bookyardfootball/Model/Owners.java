package com.nguyenquanghuy605.bookyardfootball.Model;

public class Owners {

    private long id;
    private String address;
    private String idcard;
    private String name;
    private String numberyard;
    private String phone;
    private long account;

    public Owners(){}

    public Owners(long id, String address, String idcard, String name, String numberyard, String phone ,long account) {
        this.id = id;
        this.address = address;
        this.idcard = idcard;
        this.name = name;
        this.numberyard = numberyard;
        this.phone = phone;
        this.account = account;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberyard() {
        return numberyard;
    }

    public void setNumberyard(String numberyard) {
        this.numberyard = numberyard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
