package com.app2tap.myexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    private static final String[] LIST_MONTHS = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberPicker npDate = (NumberPicker) findViewById(R.id.np_date);
        npDate.setMaxValue(31);
        npDate.setMinValue(1);
        npDate.setWrapSelectorWheel(true);

        NumberPicker npMonth = (NumberPicker) findViewById(R.id.np_month);
        npMonth.setMaxValue(LIST_MONTHS.length - 1);
        npMonth.setMinValue(0);
        npMonth.setDisplayedValues(LIST_MONTHS);
        npMonth.setWrapSelectorWheel(true);
        npMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newValue) {
                // Do something when value of numberpicker is changed
            }
        });
    }
}
