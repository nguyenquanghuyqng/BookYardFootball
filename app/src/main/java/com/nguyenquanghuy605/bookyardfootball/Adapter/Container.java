package com.nguyenquanghuy605.bookyardfootball.Adapter;

public class Container {
    public long idyard;
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
