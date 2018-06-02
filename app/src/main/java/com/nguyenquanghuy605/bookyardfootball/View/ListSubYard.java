package com.nguyenquanghuy605.bookyardfootball.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Adapter.SubYardAdapter;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.ArrayList;

public class ListSubYard extends AppCompatActivity {

    ListView listviewSubYard;
    TextView txtNameYard, txtNameOwner;
    Button btnBack;
    ArrayList<Yards> yardArrayList = new ArrayList<Yards>();
    ArrayList<Owners> ownersArrayList = new ArrayList<Owners>();
    ArrayList<SubYards> subYardsArrayList = new ArrayList<SubYards>();
    ArrayList<OptionYard> optionYardArrayList = new ArrayList<OptionYard>();
    SubYardAdapter subYardAdapter;

    private DatabaseReference databaseReferenceYard;
    private DatabaseReference databaseReferenceOwner;
    private DatabaseReference databaseReferenceSubYard;
    private DatabaseReference databaseReferenceOptionYard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_subyard);

        AnhXa();

        Initialize();
    }

    private void AnhXa(){
        listviewSubYard = (ListView)findViewById(R.id.listviewSubYard);
        txtNameYard = (TextView)findViewById(R.id.nameyard);
        txtNameOwner = (TextView)findViewById(R.id.nameowner);
        btnBack = (Button)findViewById(R.id.btnBack);
    }

    private void Initialize() {

        // Bắt sự kiện trở về list sân
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSubYard.this, ListAllYard.class);
                Container.getInstance().idyard = 0;
                startActivity(intent);
            }
        });

        // Gọi tới node cha
        databaseReferenceYard = FirebaseDatabase.getInstance().getReference().child("Yards");
        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference().child("Owners");
        databaseReferenceSubYard = FirebaseDatabase.getInstance().getReference().child("SubYards");
        databaseReferenceOptionYard = FirebaseDatabase.getInstance().getReference().child("OptionYard");

        subYardAdapter = new SubYardAdapter(this,R.layout.layout_itemsubyard_time, yardArrayList,
                ownersArrayList, optionYardArrayList, subYardsArrayList);

        // Set adapter cho listview
        listviewSubYard.setAdapter(subYardAdapter);

        Query queryYard = databaseReferenceYard.orderByChild("id");
        queryYard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        Yards yards = data.getValue(Yards.class);

                        Log.d("DataYard",data.getValue().toString());

                        yardArrayList.add(yards);

                        subYardAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Get data Yard",databaseError.getMessage());
            }
        });

        Query queryOwner = databaseReferenceOwner.orderByChild("id");
        queryOwner.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        Owners owners = data.getValue(Owners.class);

                        Log.d("DataOwner",data.getValue().toString());

                        ownersArrayList.add(owners);

                        subYardAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Get data Yard",databaseError.getMessage());
            }
        });

        Log.d("Container",Container.getInstance().idyard+"");
        Query querySubYard = databaseReferenceSubYard.orderByChild("id");
        querySubYard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Hahaha","error");
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        SubYards subYards = data.getValue(SubYards.class);

                        Log.d("DataSubYard",data.getValue().toString());
                        if(data.child("yard").getValue().equals(Container.getInstance().idyard)){
                            subYardsArrayList.add(subYards);
                        }

                        subYardAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Get data Yard",databaseError.getMessage());
            }
        });

        Query queryOption = databaseReferenceOptionYard.orderByChild("id");
        queryOption.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        OptionYard optionYard = data.getValue(OptionYard.class);

                        Log.d("DataOption",data.getValue().toString());

                        optionYardArrayList.add(optionYard);

                        subYardAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Get data Yard",databaseError.getMessage());
            }
        });

    }

}
