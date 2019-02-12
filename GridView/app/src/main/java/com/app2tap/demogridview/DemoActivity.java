package com.app2tap.demogridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Project: DemoGridView
 * Author: HUNG CODER
 * Created on: 15/09/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private GridView gvDemo;
    private static final String LIST_PHONE[] = {
            "Samsung", "Nokia", "LG", "Iphone",
            "Blackberry", "Huawei", "Xiaomi", "Oppo", "Bphone", "Mobiistar"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, LIST_PHONE);
        gvDemo = (GridView) findViewById(R.id.gv_demo);
        gvDemo.setAdapter(adapter);
        gvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long l) {
                // Do something when an item is clicked
            }
        });
    }
}

