package com.nguyenquanghuy605.bookyardfootball.Model;

public class Yards {

    private long id;
    private String image;
    private String nameyard;
    private long owner;
    private long star;

    public Yards(){}

    public Yards(long id, String image, String nameyard, long owner, long star) {
        this.id = id;
        this.image = image;
        this.nameyard = nameyard;
        this.owner = owner;
        this.star = star;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
