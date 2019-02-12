package com.app2tap.customgridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: CustomGridView
 * Author: HUNG CODER
 * Created on: 16/09/2017.
 */

public class DemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gvFace;
    private List<Face> listFace;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        listFace = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            listFace.add(new Face("Cry", R.drawable.ic_cry));
            listFace.add(new Face("Dribble", R.drawable.ic_dribble));
            listFace.add(new Face("Kiss", R.drawable.ic_kiss));
            listFace.add(new Face("Love", R.drawable.ic_love));
            listFace.add(new Face("oh", R.drawable.ic_oh));
        }

        gvFace = (GridView) findViewById(R.id.gv_face);
        myAdapter = new MyAdapter(this, listFace);
        gvFace.setAdapter(myAdapter);
        gvFace.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        listFace.remove(i);
        myAdapter.notifyDataSetChanged();
    }
}
