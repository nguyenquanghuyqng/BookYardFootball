package com.nguyenquanghuy605.bookyardfootball.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nguyenquanghuy605.bookyardfootball.Adapter.optionyard;
import com.nguyenquanghuy605.bookyardfootball.R;

public class Myyard extends AppCompatActivity {

    Button btnedit,btndanhgia,btnsancon,btnuser;
    TextView nameyard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myyard);
        nameyard= (TextView) findViewById(R.id.namyard);
        btnedit= (Button) findViewById(R.id.btnedit);
        btndanhgia= (Button) findViewById(R.id.btndanhgia);
        btnsancon= (Button) findViewById(R.id.btnsancon);
        btnuser= (Button) findViewById(R.id.btnUser);

        nameyard.setText(optionyard.getInstance().nameYardItem);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Myyard.this,edityard_onwer.class);
            }
        });
        btndanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Myyard.this,MyEvaluate.class);
            }
        });
        btnsancon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Myyard.this,subyard_owner.class);
            }
        });
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(Myyard.this,InformationYardOwner.class);
            }
        });
    }
}
