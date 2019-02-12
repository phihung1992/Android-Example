package com.app2tap.applicationcontext.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app2tap.applicationcontext.MyApplication;
import com.app2tap.applicationcontext.R;
import com.app2tap.applicationcontext.SecondActivity;

/**
 * Example: ApplicationContext.
 * Author: HUNG CODER.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_show_toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_toast:
                Intent intent = new Intent();
                intent.setClass(this, SecondActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
