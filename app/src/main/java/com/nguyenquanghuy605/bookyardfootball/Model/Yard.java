package com.nguyenquanghuy605.bookyardfootball.Model;

public class Yard {
    private String id;
    private String yardName;
    private int image;
    private String address;
    private String numberYard;

    public Yard(String id, String yardName, int image, String address, String numberYard) {
        this.id = id;
        this.yardName = yardName;
        this.image = image;
        this.address = address;
        this.numberYard = numberYard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYardName() {
        return yardName;
    }

    public void setYardName(String yardName) {
        this.yardName = yardName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberYard() {
        return numberYard;
    }

    public void setNumberYard(String numberYard) {
        this.numberYard = numberYard;
    }
}
