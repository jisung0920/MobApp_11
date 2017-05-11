package com.example.jisung.mobapp_11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> data;
    LinearLayout l1,l2;
    DatePicker datePicker;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        data = new ArrayList<String>();
        l1 = (LinearLayout)findViewById(R.id.linear1);
        l2 = (LinearLayout)findViewById(R.id.linear2);
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,data);
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
    void init(){
        datePicker = (DatePicker)findViewById(R.id.datepick);
        e1 = (EditText)findViewById(R.id.e1);
    }
    void onClick(View v){
        if(v.getId()==R.id.btn1){
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.INVISIBLE);
        }
        else if(v.getId() == R.id.btncancel){
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.INVISIBLE);
        }
    }
}
