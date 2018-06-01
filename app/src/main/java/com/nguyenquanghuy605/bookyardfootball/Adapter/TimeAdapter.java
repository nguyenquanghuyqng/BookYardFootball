package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenquanghuy605.bookyardfootball.Model.Time;

import java.util.List;

public class TimeAdapter  extends BaseAdapter{

    private Context context;
    private List<Time> timeList;
    private int layout;

    public TimeAdapter(Context context, List<Time> timeList, int layout) {
        this.context = context;
        this.timeList = timeList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return timeList.size();
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
        TextView txtviewYard;
        Button btnTime;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            //
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_item_time,null);

            holder = new ViewHolder();

//            holder.btnTime = (Button) view.findViewById(R.id.btnTime);
//            holder.txtviewYard =  (TextView) view.findViewById(R.id.txtviewYard);


            // Set tag cho View
            view.setTag(holder);
        }else {
            // Lấy phần ánh xạ lại thôi
            holder = (ViewHolder) view.getTag();
        }

        Time time = timeList.get(i);

        Log.d("Hello1",time.getTimestart()+time.getTimeend());

        holder.btnTime.setText(time.getTimestart() +"->"+ time.getTimeend());
        holder.txtviewYard.setText("1");

        Log.d("Hello2",time.getTimestart()+time.getTimeend());

        return view;
    }
}
