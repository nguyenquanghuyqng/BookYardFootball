package com.nguyenquanghuy605.bookyardfootball.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenquanghuy605.bookyardfootball.R;

public class edityard_onwer extends AppCompatActivity {

    Spinner spdssan,spdsloaisan;

    private DatabaseReference databaseReferenceOption;
    private DatabaseReference databaseReferencesubYard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edityard_onwer);
        Anhxa();


        //danh sách loại sân+sân
        databaseReferencesubYard = FirebaseDatabase.getInstance().getReference().child("SubYards");
        databaseReferenceOption = FirebaseDatabase.getInstance().getReference().child("OptionYard");


    }
    private void Anhxa(){
        spdssan= (Spinner) findViewById(R.id.spinsan);
        spdsloaisan= (Spinner) findViewById(R.id.spinloaisan);
    }
}
