package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Adapter.EditpriceAdapterRecly;
import com.nguyenquanghuy605.bookyardfootball.Adapter.optionyard;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.PriceTime;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class edityard_onwer extends AppCompatActivity {

    Spinner spdssan,spdsloaisan;

    Button btnadd,btnsave;
    EditText san5,san7;
    TextView timestart,timeend,namyard;
    RecyclerView.Adapter editpriceAdapter;
    String img;
    long star,maloaisan;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView giakhunggio;
   final private ArrayList<OptionYard> optionYardArrayList = new ArrayList<OptionYard>();
   private ArrayList<SubYards> subYardsArrayList=new ArrayList<SubYards>();
   final private ArrayList<String> loaisanlist=new ArrayList<String>();
    final private ArrayList<Long> sanlist=new ArrayList<Long>();
    private  ArrayList<PriceTime> priceTimeslist=new ArrayList<PriceTime>();
    private DatabaseReference databaseReferenceOption;
    private DatabaseReference databaseReferenceSubYard;
    private DatabaseReference databaseReferencePriceTime;
    private DatabaseReference databaseReferenceYards;
//    private DatabaseReference databaseReference;
//    private DatabaseReference databaseReferencesubYard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edityard_onwer);
        Anhxa();

         timestart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 chongiostart();
             }
         });
        timeend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonggioend();
            }
        });
        //danh sách loại sân+sân
        databaseReferenceSubYard = FirebaseDatabase.getInstance().getReference().child("SubYards");
        databaseReferenceOption = FirebaseDatabase.getInstance().getReference().child("OptionYard");
        databaseReferencePriceTime=FirebaseDatabase.getInstance().getReference().child("Prices");
        databaseReferenceYards=FirebaseDatabase.getInstance().getReference().child("Yards");
        final ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,loaisanlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdsloaisan.setAdapter(arrayAdapter);

        final ArrayAdapter arrayAdaptersan=new ArrayAdapter(this,android.R.layout.simple_spinner_item,sanlist);
        arrayAdaptersan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdssan.setAdapter(arrayAdaptersan);

        giakhunggio.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        giakhunggio.setLayoutManager(mLayoutManager);

        editpriceAdapter=new EditpriceAdapterRecly(priceTimeslist);
        giakhunggio.setAdapter(editpriceAdapter);

        databaseReferenceOption.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                if(dataSnapshot.exists()){
                    for(DataSnapshot data : nodeChild){

                        // Lấy dữ liệu từ firebase xuống đưa vào model
                        OptionYard yard = data.getValue(OptionYard.class);

                        Log.d("Yard",yard.toString());
                        Log.d("OptionYard", data.getValue().toString());
                        // Add vào List
                        optionYardArrayList.add(yard);
                        loaisanlist.add(yard.getName().toString());

                        arrayAdapter.notifyDataSetChanged();
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
        Query querySubYard = databaseReferenceSubYard.orderByChild("id");
        querySubYard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Hahaha","error");
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        SubYards subYards = data.getValue(SubYards.class);

                        Log.d("DataSubYard",data.getValue().toString());
                        if(data.child("yard").getValue().equals(optionyard.getInstance().idyard)){
                            subYardsArrayList.add(subYards);
                            sanlist.add(subYards.getId());
                        }
                        arrayAdaptersan.notifyDataSetChanged();

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Get data Yard",databaseError.getMessage());
            }
        });
        Query queryTime = databaseReferencePriceTime.orderByChild("id");
        queryTime.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        PriceTime priceTime = data.getValue(PriceTime.class);

                        Log.d("DataSubYard",data.getValue().toString());
                        if(data.child("yard").getValue().equals(optionyard.getInstance().idyard)){

                             priceTimeslist.add(priceTime);
                        }
                        editpriceAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Get data Yard",databaseError.getMessage());
            }
        });
        Query queryYards = databaseReferenceYards.orderByChild("id");
        queryYards.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        Yards yards = data.getValue(Yards.class);

                        if(data.child("id").getValue().equals(optionyard.getInstance().idyard)){
                            timestart.setText(""+yards.getTimestart());
                            timeend.setText(""+yards.getTimeend());
                            img=yards.getImage();
                            star=yards.getStar();

                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Get data Yard",databaseError.getMessage());
            }
        });



        spdssan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


//                timestart.setText(""+optionyard.getInstance().timestart);
//                timeend.setText(""+optionyard.getInstance().timeend);
                san5.setText(""+optionYardArrayList.get(0).getAddprice());
                san7.setText(""+optionYardArrayList.get(1).getAddprice());
               // Toast.makeText(edityard_onwer.this, ""+priceTimeslist.get(1).getTimestart(), Toast.LENGTH_SHORT).show();
                for(OptionYard op :optionYardArrayList){
                    if(op.getId()==subYardsArrayList.get(position).getOptionyard()){
                        spdsloaisan.setSelection(arrayAdapter.getPosition(op.getName()));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //hehe
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maloaisan=arrayAdapter.getPosition(spdsloaisan.getSelectedItem().toString());
                Savedulieu();

                Toast.makeText(edityard_onwer.this, "Cập nhật thông tin thành công", Toast.LENGTH_LONG).show();
            }
        });
        final int i=optionYardArrayList.size();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowDialog();
                Toast.makeText(edityard_onwer.this,""+arrayAdapter.getPosition(""+spdsloaisan.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
            }
        });



    }
    private  void ShowDialog(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.add_subyard);

        final Spinner adspin = (Spinner) dialog.findViewById(R.id.spinloai);

//        ArrayAdapter addAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,loaisanlist);
//        addAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adspin.setAdapter(addAdapter);

       // Toast.makeText(this, "hehe :"+addAdapter.getPosition("Sân 7 người"), Toast.LENGTH_SHORT).show();
        Button  addloai= (Button) dialog.findViewById(R.id.btnadd);

        addloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }
        });

        dialog.show();
       // addAdapter.notifyDataSetChanged();

    }
    private void Anhxa(){
        spdssan= (Spinner) findViewById(R.id.spinsan);
        spdsloaisan= (Spinner) findViewById(R.id.spinloai);
        btnadd= (Button) findViewById(R.id.btnadd);
        san5= (EditText) findViewById(R.id.san5);
        san7= (EditText) findViewById(R.id.san7);
        timestart= (TextView) findViewById(R.id.timestart);
        timeend= (TextView) findViewById(R.id.timeend);
        namyard= (TextView) findViewById(R.id.namyard);
        giakhunggio= (RecyclerView) findViewById(R.id.listkhunggio);
        btnsave= (Button) findViewById(R.id.save);
    }
    private void chongiostart(){
        final Calendar calendar=Calendar.getInstance();
        int gio=calendar.get(Calendar.HOUR_OF_DAY);
        int phut=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH");
                calendar.set(0,0,0,hourOfDay,minute);
                int x= Integer.parseInt(simpleDateFormat.format(calendar.getTime()).toString());
                int y= Integer.parseInt(timeend.getText().toString());
                if(x>=y){
                    Toast.makeText(edityard_onwer.this, "Giờ mở cửa không hợp lệ!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    timestart.setText(simpleDateFormat.format(calendar.getTime()));

           }
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
    private void chonggioend(){
        final Calendar calendar2=Calendar.getInstance();
        int gio=calendar2.get(Calendar.HOUR_OF_DAY);
        int phut=calendar2.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog2=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH");
              //  SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("HH");
                calendar2.set(0,0,0,hourOfDay,minute);
                int x= Integer.parseInt(simpleDateFormat.format(calendar2.getTime()).toString());
                int y= Integer.parseInt(timestart.getText().toString());
                if(x<=y){
                    Toast.makeText(edityard_onwer.this, "Giờ đóng cửa không hợp lệ!!", Toast.LENGTH_SHORT).show();
                }
                else {
               timeend.setText(simpleDateFormat.format(calendar2.getTime()));

            }
            }
        },gio,phut,true);
        timePickerDialog2.show();
    }
    private void Savedulieu(){
        long st= Long.parseLong(timestart.getText().toString());
        long ed= Long.parseLong(timeend.getText().toString());

        Yards yards=new Yards(optionyard.getInstance().idyard,img,optionyard.getInstance().nameYardItem,
                optionyard.getInstance().idOwner,optionyard.getInstance().comment,
                optionyard.getInstance().comment,st,ed);
        databaseReferenceYards.child(String.valueOf(optionyard.getInstance().idyard-1)).setValue(yards);
        long idsubyard= Long.parseLong(spdssan.getSelectedItem().toString());
        SubYards subyards=new SubYards(idsubyard,maloaisan+1,optionyard.getInstance().idyard);
        databaseReferenceSubYard.child(String.valueOf(optionyard.getInstance().idyard-1)).setValue(subyards);

//        long san5so= Long.parseLong(san5.getText().toString());
//        OptionYard preoptionyard=new OptionYard(1,san5so,"Sân 5 người");
//        databaseReferenceOption.child(String.valueOf(0)).setValue(preoptionyard);
        long san7so= Long.parseLong(san7.getText().toString());
        OptionYard pre2optionyard=new OptionYard(2,san7so,"Sân 7 người");
        databaseReferenceOption.child(String.valueOf(1)).setValue(pre2optionyard);


    }


}
