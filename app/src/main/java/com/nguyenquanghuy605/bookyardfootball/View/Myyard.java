package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Adapter.optionyard;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.Calendar;

public class Myyard extends AppCompatActivity {

    Button btnedit,btndanhgia,btnsancon,btnuser,btnBack;
    TextView nameyard;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myyard);

        firebaseAuth = FirebaseAuth.getInstance();

        nameyard= (TextView) findViewById(R.id.namyard);
        btnedit= (Button) findViewById(R.id.btnedit);
        btndanhgia= (Button) findViewById(R.id.btndanhgia);
        btnsancon= (Button) findViewById(R.id.btnsancon);
        btnuser= (Button) findViewById(R.id.btnUser);
        btnBack= (Button) findViewById(R.id.btnBack);



        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Myyard.this,edityard_onwer.class);
                startActivity(intent);
            }
        });
        btndanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Myyard.this,MyEvaluate.class);
                startActivity(intent1);
            }
        });
        btnsancon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Myyard.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                Intent intent2=new Intent(Myyard.this,subyard_owner.class);
                                Container.getInstance().date = day+"/"+(month+1)+"/"+year;
                                Container.getInstance().idyard =optionyard.getInstance().idyard;
                                Container.getInstance().nameYardItem = optionyard.getInstance().nameYardItem;
                                Container.getInstance().nameOwnerItem = optionyard.getInstance().nameOwnerItem;
                                Container.getInstance().numberYardItem = optionyard.getInstance().numberYardItem;
//                Container.getInstance().nameOptionYard = optionYardArrayList.get(position).getName();
                                startActivity(intent2);


                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();



            }
        });
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(Myyard.this,InformationOwner.class);
                startActivity(intent3);
                nameyard.setText(optionyard.getInstance().nameYardItem.toString());
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent=new Intent(Myyard.this,Login.class);
                startActivity(intent);
            }
        });

    }
}
