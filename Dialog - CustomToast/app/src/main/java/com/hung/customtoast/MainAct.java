package com.hung.customtoast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by HUNG on 26/4/2017.
 */

public class MainAct extends AppCompatActivity implements View.OnClickListener {
    private Button btnShowToast, btnShowCustomToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo);
        initView();
    }

    private void initView() {
        btnShowToast = (Button) findViewById(R.id.btn_show_toast);
        btnShowToast.setOnClickListener(this);
        btnShowCustomToast = (Button) findViewById(R.id.btn_show_custom_toast);
        btnShowCustomToast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_toast:
                Toast.makeText(this, "This is a toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_show_custom_toast:
                MyToast myToast = new MyToast(this);
                myToast.show();
                break;
            default:
                break;
        }
    }
}
