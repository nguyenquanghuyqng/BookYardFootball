package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nguyenquanghuy605.bookyardfootball.Model.BookYard;

import java.util.ArrayList;

public class optionyard {
    public long idyard;
    public long id;
    public long star;
    public long comment = 1;
    public long idOwner;
    public String nameYardItem;
    public String nameOwnerItem;
    public String nameOptionYard;
    public String numberYardItem;
    public String date;
    public int checkDate;
     public long timestart ;
    public long timeend ;
    public ArrayList<BookYard> bookYardList = new ArrayList<BookYard>();
    private static optionyard instance =null;
    private void optionyard() {
    }
    public static optionyard getInstance() {
        if(instance == null) {
            instance = new optionyard();
        }
        return instance;
    }
}
