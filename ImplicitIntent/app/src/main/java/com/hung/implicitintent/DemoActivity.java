package com.hung.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HUNG on 30/4/2017.
 */

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtPhone;
    private Button btnCall, btnSendMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo);
        initView();
    }

    private void initView() {
        btnCall = (Button) findViewById(R.id.btn_call);
        btnCall.setOnClickListener(this);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        btnSendMessage = (Button) findViewById(R.id.btn_send_message);
        btnSendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                doCalling();
                break;
            case R.id.btn_send_message:
                doSendMessage();
                break;
            default:
                break;
        }
    }

    private void doSendMessage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + edtPhone.getText().toString()));
        startActivity(intent);
    }

    private void doCalling() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + edtPhone.getText().toString()));
        startActivity(intent);
    }
}
