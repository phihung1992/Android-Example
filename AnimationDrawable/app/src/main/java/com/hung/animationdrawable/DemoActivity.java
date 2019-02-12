package com.hung.animationdrawable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by HUNG on 30/4/2017.
 */

public class DemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        ImageView ivBattery = (ImageView) findViewById(R.id.iv_battery);
        AnimationDrawable animation = (AnimationDrawable) ivBattery.getBackground();
        animation.start();
    }
}
