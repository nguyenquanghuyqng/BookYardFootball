package com.nguyenquanghuy605.bookyardfootball.Adapter;

import com.nguyenquanghuy605.bookyardfootball.Model.BookYard;

import java.util.ArrayList;
import java.util.List;

public class Container {
    public long idyard;
    public long id = 1;
    public long star;
    public long idOwner;
    public String nameYardItem;
    public String nameOwnerItem;
    public String numberYardItem;
    public String nameOptionYard;
    public String date;
    public int checkDate;
    public long accountid = 1;
    public long status = 1;
    public long  idsubyard = 1;
    public long timestart = 15;
    public long timeend = 16;
    public int totalMoney;
    public int keysub;
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
