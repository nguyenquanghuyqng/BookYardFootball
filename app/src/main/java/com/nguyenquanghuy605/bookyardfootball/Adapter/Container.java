package com.nguyenquanghuy605.bookyardfootball.Adapter;

import com.nguyenquanghuy605.bookyardfootball.Model.BookYard;

import java.util.ArrayList;
import java.util.List;

public class Container {
    public long idyard;
    public String nameYardItem;
    public String nameOwnerItem;
    public String numberYardItem;
    public String nameOptionYard;
    public String date;
    public int checkDate;
    public ArrayList<BookYard> bookYardList = new ArrayList<BookYard>();
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
