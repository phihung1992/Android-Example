package com.hung.datepicker;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo);
        final DatePicker dpTimer = (DatePicker) findViewById(R.id.dp_timer);

        findViewById(R.id.btn_get_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedDay = dpTimer.getDayOfMonth();
                int selectedMonth = dpTimer.getMonth() + 1;
                int selectedYeah = dpTimer.getYear();
                // Do something...
            }
        });
    }
}
