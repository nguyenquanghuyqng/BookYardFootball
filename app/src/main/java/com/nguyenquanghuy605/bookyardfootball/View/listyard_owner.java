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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.YardAdapter;
import com.nguyenquanghuy605.bookyardfootball.Adapter.YardAdapterowner;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.ArrayList;

public class listyard_owner extends AppCompatActivity {

    ListView lvYard;
    ArrayList<Yards> yardArrayList;
    YardAdapterowner yardAdapter;
    ArrayList<Owners> ownerArrayList = new ArrayList<Owners>();

    private DatabaseReference databaseReferenceYard;
    private DatabaseReference databaseReferenceOwner;

    //Button btnBookYard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listyard_owner);

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
        yardAdapter = new YardAdapterowner(this, R.layout.item_yard_owner, yardArrayList , ownerArrayList);
        lvYard.setAdapter(yardAdapter);

        lvYard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(listyard_owner.this, DetailScheduleYard.class);
                String message = "abc";
                intent.putExtra("EXTRA_MESSAGE", message);
                startActivity(intent);
            }
        });

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
                Log.d("Error Yard",databaseError.getMessage());
            }
        });

        Query query = databaseReferenceOwner.orderByChild("id");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data1 : dataSnapshot.getChildren()){
                        Owners owners = data1.getValue(Owners.class);

                        Log.d("Owners",data1.getValue().toString());

                        ownerArrayList.add(owners);

                        yardAdapter.notifyDataSetChanged();
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Owner",databaseError.getMessage());
            }
        });

    }

}
