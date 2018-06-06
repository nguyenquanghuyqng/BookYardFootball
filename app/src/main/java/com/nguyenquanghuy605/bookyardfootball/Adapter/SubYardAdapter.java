package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Model.BookYard;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.PriceTime;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;
import java.util.List;

public class SubYardAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Yards> yardList;
    private List<OptionYard> optionYardList;
    private List<SubYards> subYardsList;
    private List<BookYard> bookYardList;

    long total=0;       // Tổng tiền khi đặt sân
    int idcheck;        // Vị trí của khi  click vào checkBox
    int idBookYard=0;   // Id của BookYard
    int checked=0;      // Biến lưu khi đã book thành công
    int timeopen;       // Biến lưu giờ mở cửa

    // Mảng  id của 24 checkbox
    private int[] idCheckBox = {
            R.id.time00, R.id.time01, R.id.time02, R.id.time03,
            R.id.time04, R.id.time05, R.id.time06, R.id.time07,
            R.id.time08, R.id.time09, R.id.time10, R.id.time11,
            R.id.time12, R.id.time13, R.id.time14, R.id.time15,
            R.id.time16, R.id.time17, R.id.time18, R.id.time19,
            R.id.time20, R.id.time21, R.id.time22, R.id.time23
    };
    // Biên kiểm tra checkbox đã check chưa
    boolean check = false;

    // 24 biến boolean để check kiểm tra
    private boolean[] clickCheck = {
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
            false,false,false,false,false,false,
    };
    // Khai cáo DatabaseReference để lấy giá theo  giờ và ngày
    private DatabaseReference databaseReferencePriceTime = FirebaseDatabase.getInstance()
            .getReference().child("Prices");

    private DatabaseReference databaseReferenceYard = FirebaseDatabase.getInstance()
            .getReference().child("Yards");

    private DatabaseReference databaseReferenceBookYard = FirebaseDatabase.getInstance()
            .getReference().child("BookYard");

    public SubYardAdapter(Context context, int layout, List<Yards> yardList, List<BookYard> bookYardList,
                          List<OptionYard> optionYardList, List<SubYards> subYardsList){
        this.context = context;
        this.layout = layout;
        this.yardList = yardList;
        this.bookYardList = bookYardList;
        this.optionYardList = optionYardList;
        this.subYardsList = subYardsList;
    }

    @Override
    public int getCount() {
        return subYardsList.size();
    }

    @Override
    public Object getItem(int position) {
        return subYardsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // Có bao nhiêu biến ánh xạ thì khai báo trong class này
    // Sử dụng viewHolder để tối ưu listview
    private class ViewHolder{
        TextView txtnumYard, txtyardKind, txtTotalMoney;
        Button btnBookYard;
    }

    // Lấy dữ liệu truyền vào model Yard và BookYard
    public void GetData(){
        // Lấy ra ListYard
        Query queryYard = databaseReferenceYard.orderByChild("id");
        queryYard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Yards yards = data.getValue(Yards.class);
                        yardList.add(yards);
                    }
                }
                else{
                    Log.d("No data","Didn't get data");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error PriceTime",databaseError.getMessage());
            }
        });

        // Lấy dữ liệu đổ vào BookYardList
        Query queryBookYard = databaseReferenceBookYard.orderByChild("date").equalTo(Container.getInstance().date);
        queryBookYard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        BookYard bookYard = data.getValue(BookYard.class);
                        bookYardList.add(bookYard);
                    }
                }
                else{
                    bookYardList.isEmpty();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error",databaseError.getMessage());
            }
        });
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        GetData();
        idBookYard = subYardsList.size();
        final ViewHolder holder;
        // Thường thì lần đầu khởi tạo chạy thì biến view sẽ bằng null
        if(view == null){
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_itemsubyard_time,null);

            holder = new ViewHolder();

            holder.txtnumYard = (TextView) view.findViewById(R.id.numyard);
            holder.txtyardKind = (TextView) view.findViewById(R.id.yardkind);
            holder.txtTotalMoney = (TextView) view.findViewById(R.id.txtTotalMoney);
            holder.btnBookYard = (Button) view.findViewById(R.id.btnBookYard);

            // Set Sự kiện click cho button Đặt sân
            holder.btnBookYard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                    // Setting Dialog Title
                    alertDialog.setTitle("Thông báo xác nhận đặt sân");

                    // Setting Dialog Message
                    alertDialog.setMessage("Bạn có chắn chắn muốn đặt sân?" + total);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            try{
                                // Thực hiện truyền biến vào BookYard để thực hiện Insert lên Firebase
                                BookYard bookYard = new BookYard(Container.getInstance().accountid,Container.getInstance().date,
                                        idBookYard,total,Container.getInstance().status,
                                        Container.getInstance().idsubyard, idcheck,
                                        idcheck+1);
                                // Thực hiện Insert lên firebase
                                databaseReferenceBookYard.child(String.valueOf(idBookYard+1)).setValue(bookYard);

                                // Khi insert thành công thì biến checked = 1;
                                checked =1;
                            }
                            catch (Exception e){
                                Log.d("ErrorBookYard",e.getMessage());
                            }
                        }
                    });
                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            dialog.cancel();
                        }
                    });

                    alertDialog.show();
                }
            });
        }
        else{
            // Lấy phần ánh xạ lại thôi
            holder = (ViewHolder) view.getTag();
        }

        // Set thời gian bắt đầu và thời gian kết thúc và set màu cho các thời gian đó
        try{
            for (int c = 0; c < idCheckBox.length; c++) {
                timeopen=c;

                for(Yards yards : yardList){
                    Log.d("WhatTheHell",timeopen+"/"+yards.getTimestart()+"/"+yards.getTimeend());

                    // Kiểm tra thời gian được check vào checkbox với thời gian lấy từ bảng giá Firebase
                    if(timeopen <  yards.getTimestart() || timeopen > yards.getTimeend()){
                        checked = 1;
                        Drawable im = context.getResources().getDrawable(R.drawable.dadat);
                        im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                        // Set hình ảnh cho checkbox
                        CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[c]);
                        checkBox.setCompoundDrawables(im, null, null, null);
                        checkBox.setEnabled(false);     // Set checkbox ko được click vào
                    }
                    else {
                        checked = 0;
                        Drawable im = context.getResources().getDrawable(R.drawable.normal);
                        im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                        // Set hình ảnh cho checkbox
                        CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[c]);
                        checkBox.setCompoundDrawables(im, null, null, null);
                        checkBox.setEnabled(true);      // Set checkBox lại click lại được
                    }
                }

                if(bookYardList != null && !bookYardList.isEmpty()){
                    for (BookYard bookYard : bookYardList){
                        Log.d("CheckTime",bookYard.getTimestart()+"/"+timeopen+"/"+bookYard.getTimeend()+"");
                        if(timeopen ==  bookYard.getTimestart() && timeopen < bookYard.getTimeend()
                                && bookYard.getSubyard() == position+1){
                            Drawable im = context.getResources().getDrawable(R.drawable.dadat);
                            im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                            // Set hình ảnh cho checkbox
                            CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[c]);
                            checkBox.setCompoundDrawables(im, null, null, null);
                            checkBox.setEnabled(false);     // Set checkbox ko được click vào
                        }
                        else{
                            Log.d("CheckColor","Huhuh");
                            CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[c]);
//                            checkBox.setEnabled(true);
                        }
                    }
                }
                else{
                    Log.d("AHihi","");
                }
            }
        }catch (Exception e){
            Log.d("CheckColorError",e.getMessage());
        }

        // Xử lý từng item
        try {
            final SubYards subYards = subYardsList.get(position);
            final OptionYard optionYard = optionYardList.get(position);
            holder.txtyardKind.getTag(position);
            holder.txtyardKind.setText(optionYard.getName());
            holder.txtnumYard.getTag(position);
            holder.txtnumYard.setText("Sân"+subYards.getId());

            // Bắt sự kiện click khi người dùng click vào checkbox
            for (int a = 0; a < idCheckBox.length; a++) {
                view.findViewById(idCheckBox[a]).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            for (int i = 0; i < idCheckBox.length; i++) {
                                if (v.getId() == idCheckBox[i]) {
                                    try {
                                        // Kiểm tra checkbox
                                        if (check == false && clickCheck[i] == false) {
                                            clickCheck[i] = true;
                                        } else if (check == true && clickCheck[i] == false) {
                                            clickCheck[i] = true;
                                        } else if (check == false && clickCheck[i] == true) {
                                            clickCheck[i] = false;
                                        } else {
                                            clickCheck[i] = false;
                                        }
                                        // Kiểm tra khi clickCheck = true thì tích xanh  cái checkBox đó
                                        if (clickCheck[i]) {
                                            try {
                                                idBookYard = idBookYard +1;
                                                idcheck =i;
                                                // Thực hiện set Image khi click vào checkbox
                                                Drawable im = context.getResources().getDrawable(R.drawable.duocchon);
                                                im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                                                // Set hình ảnh cho checkbox
                                                CheckBox checkBox = (CheckBox) v.findViewById(idCheckBox[i]);
                                                checkBox.setCompoundDrawables(im, null, null, null);
                                                Container.getInstance().idsubyard = position+1;
                                                // Khi đã check vào màu xanh thì check =true
                                                check = true;
                                                // Truy vấn lấy giá theo giờ và so sánh với giờ đã được checkbox
                                                try{
                                                    Query queryPriceTime = databaseReferencePriceTime.orderByChild("yard").equalTo(Container.getInstance().idyard);
                                                    queryPriceTime.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            if(dataSnapshot.exists()){
                                                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                                                    PriceTime priceTime = data.getValue(PriceTime.class);
                                                                    // Kiểm tra thời gian được check vào checkbox với thời gian lấy từ bảng giá Firebase
                                                                    if(idcheck >= priceTime.getTimestart()&& idcheck < priceTime.getTimeend()){
                                                                        total = total + priceTime.getPrice();
                                                                        holder.txtTotalMoney.setText(total+"đồng");
                                                                    }
                                                                    else {
                                                                        Log.d("NoPrice","");
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                Log.d("No data","Didn't get data");
                                                            }
                                                        }
                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {
                                                            Log.d("Error PriceTime",databaseError.getMessage());
                                                        }
                                                    });
                                                }
                                                catch (Exception e){
                                                    Log.d("ErroPrice",e.getMessage());
                                                }

                                            } catch (Exception e) {
                                                Log.d("Error", e.getMessage());
                                            }
                                        } else {
                                            // Lấy hình ảnh từ drawable
                                            Drawable im = context.getResources().getDrawable(R.drawable.normal);
                                            im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                                            // Set hình ảnh cho checkbox
                                            CheckBox checkBox = (CheckBox) v.findViewById(idCheckBox[i]);
                                            checkBox.setCompoundDrawables(im, null, null, null);
                                            check = false;      // Trường hợp bỏ chọn thì sẽ check = false;
                                            try{
                                                Query queryPriceTime = databaseReferencePriceTime.orderByChild("yard").equalTo(Container.getInstance().idyard);
                                                queryPriceTime.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if(dataSnapshot.exists()){
                                                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                                                PriceTime priceTime = data.getValue(PriceTime.class);
                                                                // Kiểm tra thời gian được check vào checkbox với thời gian lấy từ bảng giá Firebase
                                                                if(idcheck >= priceTime.getTimestart()&& idcheck < priceTime.getTimeend()){
                                                                    // Set lại giá tiền sân
                                                                    total = total - priceTime.getPrice();
                                                                    holder.txtTotalMoney.setText(total+"đồng");
                                                                }
                                                                else {
                                                                    Log.d("NoPrice","");
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            Log.d("No data","Didn't get data");
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {
                                                        Log.d("Error PriceTime",databaseError.getMessage());
                                                    }
                                                });
                                            }
                                            catch (Exception e) {
                                                Log.d("ErrorSetText", e.getMessage());
                                            }
                                        }
                                    } catch (Exception e) {
                                        Log.d("ErrorImage", e.getMessage());
                                    }
                                }
                            }
                        }
                        catch (Exception e){
                            Log.d("Error", e.getMessage());
                        }
                    }
                });
            }
        }
        catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        return view;
    }
}