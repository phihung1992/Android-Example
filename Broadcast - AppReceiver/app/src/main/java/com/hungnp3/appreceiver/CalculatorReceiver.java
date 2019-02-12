package com.hungnp3.appreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by HUNG on 1/7/2017.
 */

public class CalculatorReceiver extends BroadcastReceiver {
    public static final String ACTION_ADD = "com.hungnp3.appsend.ACTION_ADD";
    private static final String KEY_NUM_A = "NUM_A";
    private static final String KEY_NUM_B = "NUM_B";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case ACTION_ADD:
                Bundle data = intent.getExtras();
                int numA = data.getInt(KEY_NUM_A);
                int numB = data.getInt(KEY_NUM_B);
                Toast.makeText(context, "Result : " + (numA + numB), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
