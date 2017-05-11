package com.example.jisung.mobapp_11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
    }
    void onClick(View v){
        if(v.getId() == R.id.b1){

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir()+"test.txt",true));
                bw.write("안녕하세요");
                bw.close();
                Toast.makeText(this,"저장완료",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == R.id.b2){
            try { BufferedReader br = new BufferedReader( new FileReader(getFilesDir() +"test.txt"));
                String readStr = ""; String str = null;
                while((str = br.readLine()) != null)
                    readStr += str +"\n"; br.close();
                Toast.makeText(this, readStr.substring(0, readStr.length()-1), Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            } catch(IOException e) { e.printStackTrace();
            }


        }
        else if(v.getId() == R.id.b3){

        }
        else if(v.getId() == R.id.b4){

        }
        else if(v.getId() == R.id.b5){

        }
        else if(v.getId() == R.id.b6){

        }
        else if(v.getId() == R.id.b7){

        }
    }
}
