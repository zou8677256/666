package com.example.a003;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class TimeActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.Chronometer mChronometer;
    private Button start, stop, reset, format, clear_format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time);
        // 初始化View widget
        initViews();
        // 设置监听事件
        initListeners();

    }

    private void initListeners() {
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);
        format.setOnClickListener(this);
        clear_format.setOnClickListener(this);
    }

    private void initViews() {
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        format = (Button) findViewById(R.id.set_format);
        clear_format = (Button) findViewById(R.id.clear_format);
    }

    View.OnClickListener mStartListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.start();
        }
    };

    View.OnClickListener mStopListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.stop();
        }
    };

    View.OnClickListener mResetListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.setBase(SystemClock.elapsedRealtime());
        }
    };

    View.OnClickListener mSetFormatListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.setFormat("Formatted time (%s)");
        }
    };

    View.OnClickListener mClearFormatListener = new View.OnClickListener() {
        public void onClick(View v) {
            mChronometer.setFormat(null);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mChronometer.start();// 开始计时
                break;
            case R.id.stop:
                mChronometer.stop();// 暂停计时
                break;
            case R.id.reset:
                mChronometer.setBase(SystemClock.elapsedRealtime());// 从开机到现在的毫秒数
                break;
            case R.id.set_format:
                // 需要一个String变量，并使用"%s"表示计时信息
                mChronometer.setFormat("时间累计:%s秒");
                break;
            case R.id.clear_format:
                mChronometer.setFormat(null);
                break;
        }
    }


}
