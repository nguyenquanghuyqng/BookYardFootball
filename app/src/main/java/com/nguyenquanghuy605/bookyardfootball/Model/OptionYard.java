package com.nguyenquanghuy605.bookyardfootball.Model;

import android.graphics.Path;

public class OptionYard {
    private long id;
    private long addprice;
    private String name;

    public OptionYard(){}

    public OptionYard(long id, long addprice, String name) {
        this.id = id;
        this.addprice = addprice;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAddprice() {
        return addprice;
    }

    public void setAddprice(long addprice) {
        this.addprice = addprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
