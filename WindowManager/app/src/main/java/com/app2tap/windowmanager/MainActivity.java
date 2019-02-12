package com.app2tap.windowmanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Project: WindowManager.
 * Author: HUNG CODER.
 * Created on: 27/9/2017
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
        startService(new Intent(this, MessageService.class));
        finish();
    }


    private void requestPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SYSTEM_ALERT_WINDOW) == PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 0);
            }
        }
    }
}
