package com.nguyenquanghuy605.bookyardfootball.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenquanghuy605.bookyardfootball.Adapter.TimeAdapter;
import com.nguyenquanghuy605.bookyardfootball.Model.Time;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.util.ArrayList;

public class DetailScheduleYard extends AppCompatActivity {

    ListView lvTime;
    ArrayList<Time> timeArrayList;
    TimeAdapter timeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_time);

        AnhXa();

        timeAdapter = new TimeAdapter(this, timeArrayList, R.layout.layout_listview_time);
        Toast.makeText(this, "Huy", Toast.LENGTH_SHORT).show();
        lvTime.setAdapter(timeAdapter);
    }

    private void AnhXa(){
        lvTime = (ListView)findViewById(R.id.listviewTime);

        timeArrayList = new ArrayList<>();

        timeArrayList.add(new Time("15h","16h"));
        timeArrayList.add(new Time("16h","17h"));
        timeArrayList.add(new Time("18h","19h"));
        timeArrayList.add(new Time("20h","21h"));
    }
}
