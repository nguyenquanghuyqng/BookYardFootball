package com.nguyenquanghuy605.bookyardfootball.Adapter;

public class Container {
    public long idyard;
    public String nameYardItem;
    public String nameOwnerItem;
    public String numberYardItem;
    public String nameOptionYard;
    public int date;
    public int month;
    public int year;
    private static Container instance =null;
    private void Container() {
    }
    public static Container getInstance() {
        if(instance == null) {
            instance = new Container();
        }
        return instance;
    }
}
