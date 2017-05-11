package com.example.jisung.mobapp_11;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> data;
    LinearLayout l1,l2;
    DatePicker datePicker;
    EditText e1;
    TextView t1;
    Button b1;
    String path = getExternalPath();
    String dirpath = path+"diary/";
    String tmppath="";
    String tmpmemo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        checkPermission();
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,data);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        t1.setText("등록된 메모 개수 : "+data.size());


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String filename = data.get(position).toString();
                String filepath = dirpath+data.get(position).toString();
                try {
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.INVISIBLE);
                    b1.setText("수정");
                    BufferedReader br = null;
                    br = new BufferedReader(new FileReader(filepath));
                    String readStr = "";
                    String str = null;
//                    datePicker.updateDate(04,02,11);
//                    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
//                    {
//                        public void onDateSet( DatePicker view, int year, int monthOfYear, int dayOfMonth )
//                        {
//                            // TODO Auto-generated method stub
//                            int mYear = Integer.parseInt(filename.substring(0,2));
//                            int mMonth = Integer.parseInt(filename.substring(3,5));
//                            int mDay = Integer.parseInt(filename.substring(6,8));
//                        }
//                    };

                    datePicker.init(Integer.parseInt(filename.substring(0,2)),
                            Integer.parseInt(filename.substring(3,5)),
                            Integer.parseInt(filename.substring(6,8)), null);
                    Toast.makeText(Main2Activity.this, Integer.parseInt(filename.substring(0,2))+"", Toast.LENGTH_SHORT).show();

                    while ((str = br.readLine()) != null) {
                        e1.setText(readStr += str + "\n");

                    }
                    br.close();

                    File fileD = new File(dirpath+data.get(position).toString());
                    if(fileD.delete()) {
                        data.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                dialog.setMessage("삭제하시겠습니까?").setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File fileD = new File(dirpath+data.get(position).toString());
                        if(fileD.delete()){
                            Toast.makeText(Main2Activity.this, data.get(position).toString() + "가 삭제되었습니다", Toast.LENGTH_SHORT).show();
                            data.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }).setPositiveButton("취소",null).show();
                return true;
            }
        });


    }
    void init(){
        t1 =(TextView)findViewById(R.id.tvCount);
        File file = new File(path + "diary");

        if (file.isDirectory() == false)
            file.mkdir();
        b1 = (Button)findViewById(R.id.btnsave);
        listView = (ListView)findViewById(R.id.listview);
        data = new ArrayList<String>();
        l1 = (LinearLayout)findViewById(R.id.linear1);
        l2 = (LinearLayout)findViewById(R.id.linear2);
        datePicker = (DatePicker)findViewById(R.id.datepick);
        e1 = (EditText)findViewById(R.id.e1);

        File[] files = new File(path+"diary").listFiles();
        for(File f:files)
            data.add(f.getName());

    }
    void onClick(View v){
        if(v.getId()==R.id.btn1){
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.INVISIBLE);
            b1.setText("저장");
        }
        else if(v.getId() == R.id.btnsave){
            String year =""+datePicker.getYear();
            String month = "-"+(datePicker.getMonth()+1);
            String date = "-"+datePicker.getDayOfMonth();
            if(month.length()==2)
                month="-0"+month.substring(1);
            if(date.length()==2)
                date="-0"+date.substring(1);
            String filename = year.substring(2)+month+date;
            for(int i=0;i<data.size();i++){
                if((filename+".memo.txt").equals(data.get(i))) {
                    Toast.makeText(this, "중복되었습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(dirpath + filename+".memo.txt", true));
                bw.write(e1.getText().toString());
                bw.close();
                data.add(filename+".memo.txt");
                t1.setText("등록된 메모 개수 : "+data.size());
                adapter.notifyDataSetChanged();
                Toast.makeText(this, filename+"이름으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage() + ":" + getFilesDir(), Toast.LENGTH_SHORT).show();
            }
            e1.setText("");
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.INVISIBLE);
        }
        else if(v.getId() == R.id.btncancel){
            if(b1.getText().toString().equals("수정"))
                return;
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.INVISIBLE);
        }
    }
    public String getExternalPath() {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        } else sdPath = getFilesDir() + "";
        return sdPath;
    }

    public void checkPermission() {
        int permissioninfo = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissioninfo == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "SDCard 쓰기 권한 있음", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(getApplicationContext(), "권한의 필요성 설명", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        String str = null;
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                str = "SD Card 쓰기권한 승인";
            else str = "SD Card 쓰기권한 거부";
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        return ;
    }
}
