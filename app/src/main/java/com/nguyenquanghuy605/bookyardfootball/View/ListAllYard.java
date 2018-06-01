package com.nguyenquanghuy605.bookyardfootball.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.YardAdapter;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.security.acl.Owner;
import java.util.ArrayList;

public class ListAllYard extends AppCompatActivity {

    ListView lvYard;
    ArrayList<Yards> yardArrayList;
    YardAdapter yardAdapter;
    ArrayList<Owners> ownerArrayList;

    private  DatabaseReference databaseReferenceYard;
    private DatabaseReference databaseReferenceOwner;

    //Button btnBookYard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_yard);

        AnhXa();

        Initialize();

    }
    private void AnhXa(){
        lvYard = (ListView) findViewById(R.id.listviewYard);
    }

    // Phương thức xử lý
    private void Initialize() {
        databaseReferenceYard = FirebaseDatabase.getInstance().getReference().child("Yards");
        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference().child("Owners");

        yardArrayList = new ArrayList<>();
        yardAdapter = new YardAdapter(this, R.layout.layout_item_yard, yardArrayList , ownerArrayList);
        lvYard.setAdapter(yardAdapter);

        databaseReferenceYard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                for(DataSnapshot data : nodeChild){

                    // Lấy dữ liệu từ firebase xuống đưa vào model
                    Yards yard = data.getValue(Yards.class);

                    Log.d("Yard",yard.toString());
                    Log.d("DataYard", data.getValue().toString());
                    // Add vào List
                    yardArrayList.add(yard);

                    yardAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error",databaseError.getMessage());
            }
        });


//        databaseReferenceOwner.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
//
//                for(DataSnapshot data : nodeChild){
//
//                    // Lấy dữ liệu từ firebase xuống đưa vào model
//                    Owners owner = data.getValue(Owners.class);
//
//                    Log.d("Owner", owner.toString());
//                    Log.d("Data",data.getValue().toString());
//                    // Add vào List
//                    ownerArrayList.add(owner);
//
//                    yardAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("Error",databaseError.getMessage());
//            }
//        });
    }

}
