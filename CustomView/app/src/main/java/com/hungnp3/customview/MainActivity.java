package com.hungnp3.customview;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hungnp3.indicatorview.IndicatorView;
import com.hungnp3.indicatorview.PagesLessException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Face> listFace;
    private ViewPager vpSmile;
    private IndicatorView ivList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDemoData();

        vpSmile = (ViewPager) findViewById(R.id.vp_list);
        FaceAdapter adapter = new FaceAdapter(this, listFace);
        vpSmile.setAdapter(adapter);

        ivList = (IndicatorView) findViewById(R.id.indicator);
        try {
            ivList.setViewPager(vpSmile);
        } catch (PagesLessException e) {
            e.printStackTrace();
        }
    }

    private void initDemoData() {
        listFace = new ArrayList<>();
        listFace.add(new Face("#FFFFFF", R.drawable.ic_face_smile, "Face Smile"));
        listFace.add(new Face("#FFFFFF", R.drawable.ic_big_smile, "Big Smile"));
        listFace.add(new Face("#FFFFFF", R.drawable.ic_happy, "Happy"));
        listFace.add(new Face("#FFFFFF", R.drawable.ic_happy_2, "Very Happy"));
    }
}
