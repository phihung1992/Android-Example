package com.hungnp3.demomvpm.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hungnp3.demomvpm.R;
import com.hungnp3.demomvpm.presenter.MyPresenter;

/**
 * Created by HUNG on 15/7/2017.
 */

public class MyActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private MyPresenter myPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        myPresenter = new MyPresenter();
        myPresenter.setListener(new MyPresenter.OnCallBack() {
            @Override
            public void disableEditText() {
                edtUserName.setEnabled(false);
                edtPassword.setEnabled(false);
            }
        });

        edtUserName = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                myPresenter.login(edtUserName.getText().toString(), edtPassword.getText().toString());
                break;
        }
    }
}
