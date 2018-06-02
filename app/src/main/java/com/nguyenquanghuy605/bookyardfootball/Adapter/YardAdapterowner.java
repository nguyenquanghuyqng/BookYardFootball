package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.List;

public class YardAdapterowner extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Yards> yardList;
    private List<Owners> ownerList;

    // Create a storage reference from our app
    FirebaseStorage storage1 = FirebaseStorage.getInstance();
    StorageReference storageRef = storage1.getReference();
    // Biến image
    private StorageReference imageRef;

    public YardAdapterowner(Context context, int layout, List<Yards> yardList,List<Owners> ownerList ) {
        this.context = context;
        this.layout = layout;
        this.yardList = yardList;
        this.ownerList = ownerList;
    }


    // Trả về số dòng trong listview
    @Override
    public int getCount() {
        return yardList.size();     // trả về tất cả có trong list
    }

    // Trả về đối tượng trong list
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
        ImageView imgYard;
        TextView txtyardName, txtAddress, txtNumberYard;
        Button btnBookYard;
    }

    // Trả về mỗi dòng trên Item
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        // Thường thì lần đầu khởi tạo chạy thì biến view sẽ bằng null
        if(view == null){
            //
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_yard_owner,null);

            holder = new ViewHolder();

            // Ánh xạ view
            holder.txtyardName = (TextView) view.findViewById(R.id.txtyardName);
            holder.txtAddress = (TextView) view.findViewById(R.id.txtAddress);
            holder.txtNumberYard = (TextView) view.findViewById(R.id.txtnumberYard);
            holder.imgYard = (ImageView) view.findViewById(R.id.imageviewYard);

            // Set tag cho View
            view.setTag(holder);

        }
        else{
            // Lấy phần ánh xạ lại thôi
            holder = (ViewHolder) view.getTag();
        }
        // gán giá trị
        Yards  yard = yardList.get(i);
        Log.d("Gia  tri i ",i+"");
        Owners owners = ownerList.get(i);

//        StorageReference islandRef = storageRef.child("images/island.jpg");

        imageRef = storageRef.child(yard.getImage());
        Log.d("Image" ,imageRef.toString());
        final long ONE_MEGABYTE = 1024 * 1024;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Log.d("OnSuccess ",imageRef.toString());
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.imgYard.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("Error ", exception.getMessage());
            }
        });

        holder.txtyardName.setText(yard.getNameyard());
        holder.txtAddress.setText(owners.getAddress());
        holder.txtNumberYard.setText(owners.getNumberyard());

        // Trước khi return thì gán animation cho view
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);

        return view;
    }
}
