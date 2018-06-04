package com.nguyenquanghuy605.bookyardfootball.Model;

public class Yard_Owner {

    private long idyard;
    private String image;
    private String nameyard;
    private long owner;
    private long star;
    private long comment;
    private long idowner;
    private String address;
    private String idcard;
    private String name;
    private String numberyard;
    private String phone;

    public Yard_Owner(){}

    public Yard_Owner(long idyard, String image, String nameyard, long owner,
                      long star, long comment, long idowner, String address,
                      String idcard, String name, String numberyard, String phone) {
        this.idyard = idyard;
        this.image = image;
        this.nameyard = nameyard;
        this.owner = owner;
        this.star = star;
        this.comment = comment;
        this.idowner = idowner;
        this.address = address;
        this.idcard = idcard;
        this.name = name;
        this.numberyard = numberyard;
        this.phone = phone;
    }

    public long getIdyard() {
        return idyard;
    }

    public void setIdyard(long idyard) {
        this.idyard = idyard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameyard() {
        return nameyard;
    }

    public void setNameyard(String nameyard) {
        this.nameyard = nameyard;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public long getStar() {
        return star;
    }

    public void setStar(long star) {
        this.star = star;
    }

    public long getComment() {
        return comment;
    }

    public void setComment(long comment) {
        this.comment = comment;
    }

    public long getIdowner() {
        return idowner;
    }

    public void setIdowner(long idowner) {
        this.idowner = idowner;
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
