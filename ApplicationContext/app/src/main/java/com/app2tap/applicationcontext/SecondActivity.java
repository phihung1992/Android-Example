package com.app2tap.applicationcontext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Example: ApplicationContext.
 * Author: HUNG CODER.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, String.valueOf(MyApplication.getInstance().getCount()), Toast.LENGTH_SHORT).show();
    }
}
