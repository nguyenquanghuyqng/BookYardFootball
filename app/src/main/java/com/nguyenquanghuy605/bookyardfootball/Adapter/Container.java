package com.nguyenquanghuy605.bookyardfootball.Adapter;

public class Container {
    public long idyard;
    public long id;
    public long star;
    public long idOwner;
    public String nameYardItem;
    public String nameOwnerItem;
    public String numberYardItem;
    public String nameOptionYard;
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
