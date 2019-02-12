package com.hungnp3.appsend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by HUNG on 1/7/2017.
 */

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String ACTION_ADD = "com.hungnp3.appsend.ACTION_ADD";
    private static final String KEY_NUM_A = "NUM_A";
    private static final String KEY_NUM_B = "NUM_B";
    private EditText edtNumA, edtNumB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        edtNumA = (EditText) findViewById(R.id.edt_num_a);
        edtNumB = (EditText) findViewById(R.id.edt_num_b);
        findViewById(R.id.btn_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                sendBroadCastAdd();
                break;
        }
    }

    private void sendBroadCastAdd() {
        Intent intent = new Intent();
        intent.setAction(ACTION_ADD);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_NUM_A, Integer.parseInt(edtNumA.getText().toString()));
        bundle.putInt(KEY_NUM_B, Integer.parseInt(edtNumB.getText().toString()));
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }
}
