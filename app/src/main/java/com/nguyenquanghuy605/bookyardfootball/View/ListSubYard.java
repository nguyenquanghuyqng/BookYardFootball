package com.nguyenquanghuy605.bookyardfootball.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
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
import com.nguyenquanghuy605.bookyardfootball.Model.BookYard;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;
import android.support.v4.app.DialogFragment;
import java.util.ArrayList;
import java.util.Calendar;

public class ListSubYard extends AppCompatActivity {

    ListView listviewSubYard;
    TextView txtNameYard, txtNameOwner, txtdate;
    Button btnBack;
    ImageView date;
    ArrayList<Yards> yardArrayList = new ArrayList<Yards>();
    ArrayList<Owners> ownersArrayList = new ArrayList<Owners>();
    ArrayList<SubYards> subYardsArrayList = new ArrayList<SubYards>();
    ArrayList<OptionYard> optionYardArrayList = new ArrayList<OptionYard>();
    ArrayList<BookYard> bookYardArrayList = new ArrayList<BookYard>();
    SubYardAdapter subYardAdapter;

    private DatabaseReference databaseReferenceYard;
    private DatabaseReference databaseReferenceOwner;
    private DatabaseReference databaseReferenceSubYard;
    private DatabaseReference databaseReferenceOptionYard;
    private DatabaseReference databaseReferenceBookYard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_subyard);

        AnhXa();

        Initialize();
    }

    private void AnhXa(){
        listviewSubYard = (ListView)findViewById(R.id.listviewSubYard);
        txtNameYard = (TextView)findViewById(R.id.yardnameitem);
        txtNameOwner = (TextView)findViewById(R.id.nameowneritem);
        btnBack = (Button)findViewById(R.id.btnBack);
        date = (ImageView)findViewById(R.id.lich);
        txtdate = (TextView)findViewById(R.id.date);
    }

    private void Initialize() {

        txtdate.setText(Container.getInstance().date);
        final String getdate = txtdate.getText().toString();

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
        databaseReferenceBookYard = FirebaseDatabase.getInstance().getReference().child("BookYard");



//        try{
//            for (BookYard bookYard : bookYardArrayList){
//                if(bookYard.getDate().equals(getdate)){
//                    subYardAdapter = new SubYardAdapter(this,R.layout.layout_itemsubyard_time, yardArrayList,
//                            ownersArrayList, optionYardArrayList, subYardsArrayList);
//
//                    // Set adapter cho listview
//                    listviewSubYard.setAdapter(subYardAdapter);
//                }
//            }
//        }
//        catch (Exception e){
//            Log.d("Errror" ,"Hahaha");
//        }


        subYardAdapter = new SubYardAdapter(this,R.layout.layout_itemsubyard_time, yardArrayList,
                            ownersArrayList, optionYardArrayList, subYardsArrayList);

                    // Set adapter cho listview
        listviewSubYard.setAdapter(subYardAdapter);

        Query queryYard = databaseReferenceYard.orderByChild("id");
        try{
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
        }
        catch (Exception e){
            Log.d("Error Query",e.getMessage());
        }

        Query queryBookYard = databaseReferenceBookYard.orderByChild("id");
        queryBookYard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        BookYard bookYard = data.getValue(BookYard.class);

                        Log.d("DataBookYard",data.getValue().toString());

                        bookYardArrayList.add(bookYard);

                        if(getdate.equals(bookYard.getDate())){
                            Container.getInstance().checkDate = 1;
                            Container.getInstance().bookYardList.add(bookYard);
                            Log.d("DateBook",bookYard.getDate());
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

        for (BookYard bookYard : bookYardArrayList){
            Log.d("DateBook",bookYard.getDate());
            if(bookYard.getDate().equals(getdate)){

            }
        }

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



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(ListSubYard.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtdate.setText(day + "/" + (month + 1) + "/" + year);

                    }
                }, 0, 0, 0);

            datePickerDialog.show();
            }
        });



        try{
            txtNameYard.setText(Container.getInstance().nameYardItem);
            txtNameOwner.setText(Container.getInstance().nameOwnerItem);
        }
        catch (Exception e){
            Log.d("ExceptionYard",Container.getInstance().nameYardItem);
            Log.d("ExceptionOwner",Container.getInstance().nameOwnerItem);
            Log.d("ErrorSetText" , e.getMessage());
        }

    }

}
