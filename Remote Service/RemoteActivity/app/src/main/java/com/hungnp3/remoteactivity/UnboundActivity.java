package com.hungnp3.remoteactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by HUNG on 10/7/2017.
 */

public class UnboundActivity extends AppCompatActivity {
    private static final String KEY_NUM_A = "A";
    private static final String KEY_NUM_B = "B";
    private EditText edtNumA, edtNumB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbound);
        initView();
    }

    private void initView() {
        edtNumA = (EditText) findViewById(R.id.edt_num_a);
        edtNumB = (EditText) findViewById(R.id.edt_num_b);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.hungnp3.remoteservice", "com.hungnp3.remoteservice.UnboundService");
                intent.putExtra(KEY_NUM_A, edtNumA.getText().toString());
                intent.putExtra(KEY_NUM_B, edtNumB.getText().toString());
                startService(intent);
            }
        });
    }
}
