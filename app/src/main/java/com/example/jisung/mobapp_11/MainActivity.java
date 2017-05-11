package com.example.jisung.mobapp_11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    void onClick(View v){
        if(v.getId()==R.id.bt1){
            Intent intent = new Intent(this,FileActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
    }
}
