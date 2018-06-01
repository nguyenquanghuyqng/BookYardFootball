package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.content.Context;
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

import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.security.acl.Owner;
import java.util.List;

public class YardAdapter  extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Yards> yardList;
    private List<Owners> ownerList;

    public YardAdapter(Context context, int layout, List<Yards> yardList,List<Owners> ownerList ) {
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
        ViewHolder holder;

        // Thường thì lần đầu khởi tạo chạy thì biến view sẽ bằng null
        if(view == null){
            //
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_item_yard,null);

            holder = new ViewHolder();

            // Ánh xạ view
            holder.txtyardName = (TextView) view.findViewById(R.id.txtyardName);
            holder.txtAddress = (TextView) view.findViewById(R.id.txtAddress);
            holder.txtNumberYard = (TextView) view.findViewById(R.id.txtnumberYard);
            holder.imgYard = (ImageView) view.findViewById(R.id.imageviewYard);
            holder.btnBookYard = (Button) view.findViewById(R.id.btnBookYard);

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
//        Owner owner = ownerList.get(i);

        holder.txtyardName.setText(yard.getNameyard());
//        holder.txtAddress.setText(owner.getAddress());
//        holder.txtNumberYard.setText(owner.getNumberYard());
//        holder.imgYard.setImageResource(Integer.parseInt(yard.getImage())); // sai giồi

        // Trước khi return thì gán animation cho view
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);

        return view;
    }
}
