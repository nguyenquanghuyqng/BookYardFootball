package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.List;

public class SubYardAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Yards> yardList;
    private List<Owners> ownerList;
    private List<OptionYard> optionYardList;
    private List<SubYards> subYardsList;

    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    // Biến image
    private StorageReference imageRef;

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
        ImageView imgYard;
        TextView txtyardName, txtAddress, txtNumberYard;
        Button btnBookYard;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        // Thường thì lần đầu khởi tạo chạy thì biến view sẽ bằng null
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_itemsubyard_time,null);

            holder = new ViewHolder();
        }
        else{
            // Lấy phần ánh xạ lại thôi
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }
}
