package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.nguyenquanghuy605.bookyardfootball.Adapter.optionyard;
import com.nguyenquanghuy605.bookyardfootball.Model.OptionYard;
import com.nguyenquanghuy605.bookyardfootball.Model.SubYards;
import com.nguyenquanghuy605.bookyardfootball.Model.Time;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class edityard_onwer extends AppCompatActivity {

    Spinner spdssan,spdsloaisan;
    Button btnadd;
    EditText san5,san7;
    TextView timestart,timeend,namyard;
   final private ArrayList<OptionYard> optionYardArrayList = new ArrayList<OptionYard>();
   private ArrayList<SubYards> subYardsArrayList=new ArrayList<SubYards>();
   final private ArrayList<String> loaisanlist=new ArrayList<String>();
    final private ArrayList<Long> sanlist=new ArrayList<Long>();
    private DatabaseReference databaseReferenceOption;
    private DatabaseReference databaseReferenceSubYard;
    private DatabaseReference databaseReferenceTime;
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
                chongiostart();
            }
        });
        //danh sách loại sân+sân
        databaseReferenceSubYard = FirebaseDatabase.getInstance().getReference().child("SubYards");
        databaseReferenceOption = FirebaseDatabase.getInstance().getReference().child("OptionYard");
        databaseReferenceTime=FirebaseDatabase.getInstance().getReference().child("Time");
        final ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,loaisanlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdsloaisan.setAdapter(arrayAdapter);

        final ArrayAdapter arrayAdaptersan=new ArrayAdapter(this,android.R.layout.simple_spinner_item,sanlist);
        arrayAdaptersan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdssan.setAdapter(arrayAdaptersan);

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
        Query queryTime = databaseReferenceTime.orderByChild("id");
        queryTime.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        Time time = data.getValue(Time.class);

                        Log.d("DataSubYard",data.getValue().toString());
                        if(data.child("yard").getValue().equals(optionyard.getInstance().idyard)){

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



        spdssan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // namyard.setText(optionyard.getInstance().nameYardItem);
                san5.setText(""+optionYardArrayList.get(0).getAddprice()+" đ");
                san7.setText(""+optionYardArrayList.get(1).getAddprice()+" đ");
               // Toast.makeText(edityard_onwer.this, ""+sanlist.get(position), Toast.LENGTH_SHORT).show();               // spdsloaisan.setSelection();
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
        final int i=optionYardArrayList.size();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gialoaisan();

                Toast.makeText(edityard_onwer.this,""+optionyard.getInstance().idyard, Toast.LENGTH_SHORT).show();
            }
        });



    }
    private  void gialoaisan(){

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
    }
    private void chongiostart(){
        final Calendar calendar=Calendar.getInstance();
        int gio=calendar.get(Calendar.HOUR_OF_DAY);
        int phut=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                calendar.set(0,0,0,hourOfDay,minute);
                timestart.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
    private void chonggioend(){
        final Calendar calendar=Calendar.getInstance();
        int gio=calendar.get(Calendar.HOUR_OF_DAY);
        int phut=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                calendar.set(0,0,0,hourOfDay,minute);
                timeend.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }



}
