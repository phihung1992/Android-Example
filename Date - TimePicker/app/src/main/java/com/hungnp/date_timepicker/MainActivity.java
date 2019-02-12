package com.hungnp.date_timepicker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by HUNG on 05/03/2017.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        final TimePicker tpClock = (TimePicker) findViewById(R.id.tp_clock);
        tpClock.setIs24HourView(true);

        findViewById(R.id.btn_set_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // With API < 23
                int currentHour = tpClock.getCurrentHour();
                int currentMinutes = tpClock.getCurrentMinute();
            }
        });

    }
}
