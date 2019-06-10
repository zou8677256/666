package com.example.a003;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
    }
}
//public class TitleLayout extends LinearLayout{
//    public TitleLayout(Context context, AttributeSet attrs){
//        super(context,attrs);
//        LayoutInflater.from(context).inflate(R.layout.activity_test2,this);
//    }
//}