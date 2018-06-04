package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nguyenquanghuy605.bookyardfootball.Model.BookYard;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;
import com.nguyenquanghuy605.bookyardfootball.View.ListSubYard;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.security.AccessController.getContext;

public class SubYardAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private int layout;
    private List<Yards> yardList;
    private List<Owners> ownerList;
    private List<OptionYard> optionYardList;
    private List<SubYards> subYardsList;
    private List<BookYard> bookYardList;
    private CheckBox ck0001;

    int click = 1;
    int numClick = 0;
    int temp = 25;
    long total=0;
    // Create a storage reference from our app
    FirebaseStorage storage1 = FirebaseStorage.getInstance();
    StorageReference storageRef = storage1.getReference();
    // Biến image
    private StorageReference imageRef;

    private String[] timeCheckBox = {};

    private DatabaseReference databaseReferenceBookYard;

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

    public SubYardAdapter(Context context, int layout, List<Yards> yardList, List<Owners> ownerList,
                          List<OptionYard> optionYardList, List<SubYards> subYardsList){
        this.context = context;
        this.layout = layout;
        this.yardList = yardList;
        this.ownerList = ownerList;
        this.optionYardList = optionYardList;
        this.subYardsList = subYardsList;
    }

    @Override
    public int getCount() {
        return subYardsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Có bao nhiêu biến ánh xạ thì khai báo trong class này
    // Sử dụng viewHolder để tối ưu listview
    private class ViewHolder{
        TextView txtnumYard, txtyardKind, txtTotalMoney;
        Button btnBookYard;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int click =1;
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
//            txtMoney = (TextView) view.findViewById(R.id.txtTotalMoney);

            try{
                for (int a = 0; a < idCheckBox.length; a++) {
                    view.findViewById(idCheckBox[a]).setOnClickListener(this);
                }
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }

            holder.btnBookYard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                    // Setting Dialog Title
                    alertDialog.setTitle("Thông báo xác nhận đặt sân");

                    // Setting Dialog Message
                    alertDialog.setMessage("Bạn có chắn chắn muốn đặt sân?");

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Log.d("Hahahahaha","Error");
                            // Get data checkbox
                            try{
                                Log.d("BookYard","Hihi");

                                // Thực hiện truyền biến vào BookYard để thực hiện Insert lên Firebase
                                // Done date and Account 1, id,
                                BookYard bookYard = new BookYard(Container.getInstance().accountid,Container.getInstance().date,
                                        subYardsList.size(),total,Container.getInstance().status,
                                        Container.getInstance().idsubyard, Container.getInstance().timestart,
                                        Container.getInstance().timeend);

                                // Thực hiện Insert lên firebase
                                databaseReferenceBookYard = FirebaseDatabase.getInstance().getReference().child("BookYard");
                                databaseReferenceBookYard.child(String.valueOf(subYardsList.size()+1)).setValue(bookYard);
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

                    // Showing Alert Message
                    alertDialog.show();
                }
            });
        }
        else{
            // Lấy phần ánh xạ lại thôi
            holder = (ViewHolder) view.getTag();
        }
//        holder.txtTotalMoney.setText(total+"đ");

        return view;
    }

    // Hàm kiểm tra ngày rồi show ra list sub yard
    public void ShowView(String date){
        String date1 = null;
        for (BookYard bookYard : Container.getInstance().bookYardList){
            Log.d("TestBook",bookYard.getDate());
            if(Container.getInstance().date.equals(bookYard.getDate())){
                Log.d("NhanRoi","Haha");
                date1 = bookYard.getDate().toString();
            }
        }

        if(date.equals(date1)){

        }
        else{

        }

    }

    // Bắt sự kiện click cho checkBox
    @Override
    public void onClick(View view) {
        Log.d("HelloHuy","Hahaha");
        final ViewHolder holder = new ViewHolder();

        try{
            int id = view.getId();

            for(int i =0; i < idCheckBox.length;i++){
                if(id == idCheckBox[i]){
                    try {
                        Log.d("ChuaXacNhan", "Hahaha");
                        if(check == false && clickCheck[i] ==false){
                            clickCheck[i] = true;
                        }
                        else if(check == true && clickCheck[i] ==false ){
                            clickCheck[i] = true;
                        }
                        else if(check == false && clickCheck[i] ==true){
                            clickCheck[i] = false;
                        }
                        else {
                            clickCheck[i] = false;
                        }

                        if(clickCheck[i]){
                            try {
                                Drawable im = context.getResources().getDrawable(R.drawable.chuaxacnhan);
                                im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                                // Set hình ảnh cho checkbox
                                CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[i]);
                                checkBox.setCompoundDrawables(im, null, null, null);
                                check = true;

                                total = total+i;
                                Log.d("TotalMoney", total + "");
                            }
                            catch (Exception e){
                                Log.d("ErrorSetText",e.getMessage());
                            }
                        }
                        else {
                            // Lấy hình ảnh từ drawable
                            Drawable im = context.getResources().getDrawable(R.drawable.normal);
                            im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                            // Set hình ảnh cho checkbox
                            CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[i]);
                            checkBox.setCompoundDrawables(im, null, null, null);
                            check = false;
                            try{
                                total = total+i;
                                Log.d("TotalMoney",total+"");
//                                txtMoney = (TextView) view.findViewById(R.id.txtTotalMoney);
//                                txtMoney.setText("");
                            }
                            catch (Exception e){
                                Log.d("ErrorSetText",e.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        Log.d("ErrorImage", e.getMessage());
                    }
                }
            }

        }catch (Exception e){
            Log.d("Error","Onclick");
        }
    }
}