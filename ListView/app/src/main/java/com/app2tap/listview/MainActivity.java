package com.app2tap.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String[] LIST_FRUITS = {
            "Apple",
            "Orange",
            "Banana",
            "Coconut",
            "Mango",
            "Watermelon",
            "Cherry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, LIST_FRUITS);
        ListView lvFruits = (ListView) findViewById(R.id.lv_fruit);
        lvFruits.setAdapter(adapter);
        lvFruits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Do something when an item of listview is clicked
            }
        });
    }
}
