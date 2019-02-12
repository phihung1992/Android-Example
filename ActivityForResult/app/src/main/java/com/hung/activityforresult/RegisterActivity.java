package com.hung.activityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HUNG on 29/4/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    public static final String KEY_USERNAME = "USERNAME";
    public static final String KEY_PASSWORD = "PASSWORD";
    private EditText edtUsername, edtPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToLogin();
            }
        });
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
    }

    private void sendDataToLogin() {
        Intent intent = new Intent();
        intent.putExtra(KEY_USERNAME, edtUsername.getText().toString());
        intent.putExtra(KEY_PASSWORD, edtPassword.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
