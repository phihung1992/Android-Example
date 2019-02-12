package com.hungnp3.remoteactivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hungnp3.common.*;

/**
 * Created by HUNG on 11/7/2017.
 */

public class BoundActivity extends AppCompatActivity {
    private EditText edtNumA, edtNumB;
    private ISimpleCalcu iSimpleCalcu;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iSimpleCalcu = ISimpleCalcu.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);
        initView();
        connectionService();
    }

    private void connectionService() {
        Intent intent = new Intent();
        intent.setClassName("com.hungnp3.remoteservice", "com.hungnp3.remoteservice.BoundService");
        boolean isConnected = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        if (isConnected) {
            Toast.makeText(this, "Service is connected!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        edtNumA = (EditText) findViewById(R.id.edt_num_a);
        edtNumB = (EditText) findViewById(R.id.edt_num_b);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtNumA.getText().toString());
                int b = Integer.parseInt(edtNumB.getText().toString());

                try {
                    int result = iSimpleCalcu.add(a, b);
                    Toast.makeText(BoundActivity.this, "Result: " + result, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
