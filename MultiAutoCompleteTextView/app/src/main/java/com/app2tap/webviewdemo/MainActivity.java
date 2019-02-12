package com.app2tap.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String[] FRUITS = new String[]{
            "Banana",
            "Orange",
            "Avocado",
            "Pineapple",
            "Lemon",
            "Grape",
            "Coconut",
            "Mango",
            "Apple"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, FRUITS);
        MultiAutoCompleteTextView textView = (MultiAutoCompleteTextView) findViewById(R.id.atv_fruits);
        textView.setAdapter(adapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }
}
