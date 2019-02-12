package com.app2tap.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Project name: TabLayout
 * Author: app2tap
 * Created date: 4/8/2017.
 */

public class MyActivity extends AppCompatActivity {
    private ViewPager mVpDemo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_demo);
        initView();
    }

    private void initView() {
        mVpDemo = (ViewPager) findViewById(R.id.vp_demo);
        mVpDemo.setAdapter(new MyAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_demo);
        tabLayout.setupWithViewPager(mVpDemo);
    }
}
