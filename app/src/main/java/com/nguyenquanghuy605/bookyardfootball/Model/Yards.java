package com.nguyenquanghuy605.bookyardfootball.Model;

public class Yards {

    private long id;
    private String image;
    private String nameyard;
    private long owner;
    private long star;
    private long comment;
    private long timestart;
    private long timeend;

    public Yards(){}

    public Yards(long id, String image, String nameyard, long owner, long star, long comment, long timestart, long timeend) {
        this.id = id;
        this.image = image;
        this.nameyard = nameyard;
        this.owner = owner;
        this.star = star;
        this.comment = comment;
        this.timestart = timestart;
        this.timeend = timeend;
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

    public long getComment() {
        return comment;
    }

    public void setComment(long comment) {
        this.comment = comment;
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
