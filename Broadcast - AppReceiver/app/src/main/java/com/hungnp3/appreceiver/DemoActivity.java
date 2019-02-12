package com.hungnp3.appreceiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HUNG on 1/7/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private CalculatorReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        mReceiver = new CalculatorReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CalculatorReceiver.ACTION_ADD);
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
