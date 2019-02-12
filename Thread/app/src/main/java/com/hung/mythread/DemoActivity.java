package com.hung.mythread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HUNG on 1/5/2017.
 */

public class DemoActivity extends AppCompatActivity {
    public static final int MESSAGE_COUNT_DOWN = 1001;
    private TextView tvTimer;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        initHandler();
    }

    private void initView() {
        tvTimer = (TextView) findViewById(R.id.tv_timer);
        findViewById(R.id.btn_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCountDown();
            }
        });
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_COUNT_DOWN:
                        tvTimer.setText(String.valueOf(msg.arg1));
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void doCountDown() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 10;
                do {
                    time--;

                    Message msg = new Message();
                    msg.what = MESSAGE_COUNT_DOWN;
                    msg.arg1 = time;
                    mHandler.sendMessage(msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } while (time > 0);
            }

        });
        thread.start();
    }
}
