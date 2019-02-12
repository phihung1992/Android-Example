package com.hung.myviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by HUNG on 2/5/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private ArrayList<Face> listFace;
    private ViewPager vpSmile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initDemoData();

        vpSmile = (ViewPager) findViewById(R.id.vp_face);
        FaceAdapter adapter = new FaceAdapter(this, listFace);
        vpSmile.setAdapter(adapter);
    }

    private void initDemoData() {
        listFace = new ArrayList<>();
        listFace.add(new Face("#2196F3", R.drawable.ic_face_smile, "Face Smile"));
        listFace.add(new Face("#009688", R.drawable.ic_big_smile, "Big Smile"));
        listFace.add(new Face("#FFC107", R.drawable.ic_happy, "Happy"));
        listFace.add(new Face("#FF5722", R.drawable.ic_happy_2, "Very Happy"));
    }
}
