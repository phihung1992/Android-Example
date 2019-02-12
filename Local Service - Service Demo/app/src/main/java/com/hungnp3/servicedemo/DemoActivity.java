package com.hungnp3.servicedemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by HUNG on 2/7/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private EditText edtNumA, edtNumB;
    private ServiceConnection serviceConnection;
    private boolean isConnected;
    private MyService myService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        connectService();
    }

    private void connectService() {
        Intent intent = new Intent(this, MyService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder myBinder = (MyService.MyBinder) service;
                myService = myBinder.getService();
                isConnected = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isConnected = false;
                myService = null;
            }
        };
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
    }

    private void initView() {
        edtNumA = (EditText) findViewById(R.id.edt_num_a);
        edtNumB = (EditText) findViewById(R.id.edt_num_b);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected) {
                    return;
                }
                int result = myService.add(Integer.parseInt(edtNumA.getText().toString()),
                        Integer.parseInt(edtNumB.getText().toString()));
                Toast.makeText(myService, "Result: " + result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
