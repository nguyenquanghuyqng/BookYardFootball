package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.content.Context;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.List;

import static java.security.AccessController.getContext;

public class SubYardAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private int layout;
    private List<Yards> yardList;
    private List<Owners> ownerList;
    private List<OptionYard> optionYardList;
    private List<SubYards> subYardsList;
    private CheckBox ck0001;

    int click = 1;
    // Create a storage reference from our app
    FirebaseStorage storage1 = FirebaseStorage.getInstance();
    StorageReference storageRef = storage1.getReference();
    // Biến image
    private StorageReference imageRef;

    private int[] idCheckBox = {
            R.id.time00, R.id.time01, R.id.time02, R.id.time03,
            R.id.time04, R.id.time05, R.id.time06, R.id.time07,
            R.id.time08, R.id.time09, R.id.time10, R.id.time11,
            R.id.time12, R.id.time13, R.id.time14, R.id.time15,
            R.id.time16, R.id.time17, R.id.time18, R.id.time19,
            R.id.time20, R.id.time21, R.id.time22, R.id.time23
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
        TextView txtnumYard, txtyardKind;
        Button btnTotal;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int click =1;
        final ViewHolder holder;
        // Thường thì lần đầu khởi tạo chạy thì biến view sẽ bằng null
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_itemsubyard_time,null);

            holder = new ViewHolder();

            holder.txtnumYard = (TextView) view.findViewById(R.id.numyard);
            holder.txtyardKind = (TextView) view.findViewById(R.id.yardkind);
            try{
                for (int a = 0; a < idCheckBox.length; a++) {
                    view.findViewById(idCheckBox[a]).setOnClickListener(this);
                }
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        else{
            // Lấy phần ánh xạ lại thôi
            holder = (ViewHolder) view.getTag();
        }

//        holder.txtyardKind.setText(Container.getInstance().nameOptionYard);

        return view;
    }

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
                        if (click == 1) {
                            // Lấy hình ảnh từ drawable
                            Drawable im = context.getResources().getDrawable(R.drawable.chuaxacnhan);
                            im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                            // Set hình ảnh cho checkbox
                            CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[i]);
                            checkBox.setCompoundDrawables(im, null, null, null);
                            click = 2;
                        }
                        else {
                            // Lấy hình ảnh từ drawable
                            Drawable im = context.getResources().getDrawable(R.drawable.normal);
                            im.setBounds(0, 0, im.getIntrinsicWidth(), im.getIntrinsicHeight());

                            // Set hình ảnh cho checkbox
                            CheckBox checkBox = (CheckBox) view.findViewById(idCheckBox[i]);
                            checkBox.setCompoundDrawables(im, null, null, null);

                            click = 1;
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
