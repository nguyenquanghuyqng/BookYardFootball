package com.nguyenquanghuy605.bookyardfootball.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenquanghuy605.bookyardfootball.Adapter.YardAdapter;
import com.nguyenquanghuy605.bookyardfootball.Model.Yard;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.ArrayList;

public class ListAllYard extends AppCompatActivity {

    ListView lvYard;
    ArrayList<Yard> yardArrayList;
    YardAdapter yardAdapter;

//    private DatabaseReference mDatabase;

    //Button btnBookYard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_yard);

        AnhXa();

        yardAdapter = new YardAdapter(this, R.layout.layout_item_yard, yardArrayList);
        lvYard.setAdapter(yardAdapter);
    }
    private void AnhXa(){
            lvYard = (ListView) findViewById(R.id.listviewYard);
            //btnBookYard  = (Button) findViewById(R.id.btnBookYard);
            yardArrayList  = new ArrayList<>();

            yardArrayList.add(new Yard("001", "Sân SPKT", R.drawable.sandaihoc,"Ho Chi Minh", "6"));
            yardArrayList.add(new Yard("002", "Sân Hiệp Phú", R.drawable.sanbong,"Ho Chi Minh", "4"));
            yardArrayList.add(new Yard("003", "Sân Thiếu Nhi", R.drawable.santhieunhi,"Ho Chi Minh", "3"));
            yardArrayList.add(new Yard("004", "Sân Quốc Tế", R.drawable.santruong,"Ho Chi Minh", "8"));
            yardArrayList.add(new Yard("005", "Sân Bóng AT", R.drawable.sanat,"Ho Chi Minh", "3"));
            yardArrayList.add(new Yard("006", "Sân Việt Thắng", R.drawable.sanvietthang,"Ho Chi Minh", "2"));
            yardArrayList.add(new Yard("007", "Sân Cao Đẳng", R.drawable.sanbonghiepphu,"Ho Chi Minh", "3"));

            lvYard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(ListAllYard.this, "Huy ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ListAllYard.this, SearchYard.class);
                    startActivity(intent);
                }
            });

//        btnBookYard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ListAllYard.this, "Huy ", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ListAllYard.this, SearchYard.class);
//                startActivity(intent);
//            }
//        });
    }
}
