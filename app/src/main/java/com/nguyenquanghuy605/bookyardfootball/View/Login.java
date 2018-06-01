package com.nguyenquanghuy605.bookyardfootball.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenquanghuy605.bookyardfootball.Adapter.YardAdapter;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

public class Login extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

    }
}
