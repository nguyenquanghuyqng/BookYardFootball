package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Collections;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Adapter.YardAdapter;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.Yard_Owner;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListAllYard extends AppCompatActivity {

    ListView lvYard;
    ArrayList<Yards> yardArrayList = new ArrayList<Yards>();
    YardAdapter yardAdapter;
    ArrayList<Owners> ownerArrayList = new ArrayList<Owners>();
    ArrayList<OptionYard> optionYardArrayList = new ArrayList<OptionYard>();

    Calendar calendar;

    private DatabaseReference databaseReferenceYard;
    private DatabaseReference databaseReferenceOwner;
    private DatabaseReference databaseReferenceOption;


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
        databaseReferenceOption = FirebaseDatabase.getInstance().getReference().child("OptionYard");

        yardAdapter = new YardAdapter(this, R.layout.layout_item_yard, yardArrayList , ownerArrayList);
        lvYard.setAdapter(yardAdapter);

        databaseReferenceYard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                if(dataSnapshot.exists()){
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
                else {
                    Log.d("Not have data yard" ,"Haha");
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
                }else{
                    Log.d("Not have data" ,"Haha");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Owner",databaseError.getMessage());
            }
        });

        Query queryOption = databaseReferenceOption.orderByChild("id");
        queryOption.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data1 : dataSnapshot.getChildren()){
                        OptionYard opt = data1.getValue(OptionYard.class);

                        Log.d("OptionYard",data1.getValue().toString());

                        optionYardArrayList.add(opt);

//                        yardAdapter.notifyDataSetChanged();
                    }
                }else{
                    Log.d("Not have data" ,"Haha");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Owner",databaseError.getMessage());
            }
        });

        // Set Onclick Item List
        lvYard.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                Log.d("Date/Month/Year",dayOfMonth+"/"+month+"/"+year+"");

                DatePickerDialog datePickerDialog = new DatePickerDialog(ListAllYard.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            Intent intent = new Intent(ListAllYard.this, ListSubYard.class);
                            Container.getInstance().date = day+"/"+(month+1)+"/"+year;
                            Container.getInstance().idyard = position + 1;
                            Container.getInstance().nameYardItem = yardArrayList.get(position).getNameyard();
                            Container.getInstance().nameOwnerItem = ownerArrayList.get(position).getName();
                            Container.getInstance().numberYardItem = ownerArrayList.get(position).getNumberyard();
//                Container.getInstance().nameOptionYard = optionYardArrayList.get(position).getName();


                            Log.d("YardPage",(yardArrayList.get(position).getNameyard())+" "+ownerArrayList.get(position).getName());
                            startActivity(intent);
                        }
                    }, year, month, dayOfMonth);

                datePickerDialog.show();


            }
        });
    }

}