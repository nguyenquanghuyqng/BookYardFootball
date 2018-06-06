package com.nguyenquanghuy605.bookyardfootball.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.nguyenquanghuy605.bookyardfootball.Model.PriceTime;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.ArrayList;
import java.util.List;

public class EditpriceAdapterRecly extends RecyclerView.Adapter<EditpriceAdapterRecly.RecyclerViewHolder> {
    private List<PriceTime> priceList;
    FirebaseStorage storage1 = FirebaseStorage.getInstance();

    public EditpriceAdapterRecly(List<PriceTime> priceList) {
        this.priceList =priceList ;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_editpice_owner, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        PriceTime priceTime=priceList.get(position);

        // truyền path hình vào và lấy hình


        holder.khungstart.setText(""+priceTime.getTimestart());
        holder.khungend.setText(""+priceTime.getTimeend());
        holder.khunggia.setText(""+priceTime.getPrice());
    }


    @Override
    public int getItemCount() {
        return priceList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        EditText khungstart,khungend,khunggia;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            khungstart= (EditText) itemView.findViewById(R.id.khungstart);
            khungend= (EditText) itemView.findViewById(R.id.khungend);
            khunggia= (EditText) itemView.findViewById(R.id.gia);
        }
    }
}
