package com.hungnp3.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by HUNG on 10/7/2017.
 */

public class UnboundService extends Service {
    private static final String KEY_NUM_A = "A";
    private static final String KEY_NUM_B = "B";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String numA = intent.getStringExtra(KEY_NUM_A);
        String numB = intent.getStringExtra(KEY_NUM_B);
        int result = Integer.parseInt(numA) + Integer.parseInt(numB);
        Toast.makeText(this, "Result = " + result, Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }
}
