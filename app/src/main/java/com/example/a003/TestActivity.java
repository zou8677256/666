package com.example.a003;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private MyDatabaseHelper dbHelper;
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    @Override
//    protected void onCreate(Bundle savedInstanceState) {                    //点击按钮跳转到拨号码界面
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//        Button button1=(Button) findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(Intent.ACTION_DIAL);  //系统自带的电话界面
//                intent.setData(Uri.parse("tel:15255598895"));    //手机号码
//                startActivity(intent);
//            }
//        });
//    }

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button =(Button) findViewById(R.id.button1);
        editText=(EditText)findViewById(R.id.edit01);       //显示编辑框里面的文字

        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            Toast.makeText(this,"恢复成功",Toast.LENGTH_SHORT).show();
        }

        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        Button createDatebase=(Button) findViewById(R.id.button2);
        createDatebase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbHelper.getWritableDatabase();
            }
        });

        Button addData=(Button)findViewById(R.id.button3) ;
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //开始组装第一条数据
                values.put("name","THE Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);//插入第一条数据
                values.clear();
                //开始组装第二条数据
                values.put("name","The Lost Symbol");
                values.put("author","Zou hang ");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);//插入第二条数据
            }
        });


        imageView=(ImageView)findViewById(R.id.image01); //显示图片
        progressBar =(ProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(this);

        ActionBar actionBar =getSupportActionBar();   //隐藏系统自带的标题栏
        if(actionBar!=null){
            actionBar.hide();
        }
    }

   @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
              //  imageView.setImageResource(R.drawable.b6);         //按下按钮 显示另外一张图片
//                String inputText=editText.getText().toString();      //按下按钮讲文本框中输入的文字  弹出来
//                Toast.makeText(TestActivity.this, inputText,Toast.LENGTH_SHORT
//                        ).show();

//                if(progressBar.getVisibility()==View.GONE){    //点击进度条的显示或者消失
//                    progressBar.setVisibility(View.VISIBLE);
//                }else{
//                    progressBar.setVisibility(View.GONE);
//                }

//                int progress=progressBar.getProgress();     //每次点击 进度条进步10%
//                progress =progress+10;
//                progressBar.setProgress(progress);

                AlertDialog.Builder dialog =new AlertDialog.Builder(TestActivity.this);   //点击按钮 弹出对话框
                dialog.setTitle("This is Dialog");
                dialog.setMessage("你爱我么？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("爱", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("很爱", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
                default:
                    break;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText=editText.getText().toString();
        save(inputText);
    }
    public void save(String inputText){
        FileOutputStream out =null;
        BufferedWriter writer=null;
        try{
            out =openFileOutput("data",Context.MODE_PRIVATE);
            writer =new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in =null;
        BufferedReader reader =null;
        StringBuilder content =new StringBuilder();
        try{
            in = openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);

            }

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }return content.toString();
    }

//    @Override
//    protected void onCreate(Bundle savedInstance)
}
