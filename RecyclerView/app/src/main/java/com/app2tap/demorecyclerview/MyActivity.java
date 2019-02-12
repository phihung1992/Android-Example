package com.app2tap.demorecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: DemoRecyclerView2.
 * Author: HUNG CODER.
 * Created on: 28/9/2017
 */

public class MyActivity extends AppCompatActivity implements FaceAdapter.OnCallBack {
    private RecyclerView mRcvFace;
    private FaceAdapter mAdapter;
    private List<Face> mListFace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        mListFace = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            mListFace.add(new Face("Cry", R.drawable.ic_cry));
            mListFace.add(new Face("Dribble", R.drawable.ic_dribble));
            mListFace.add(new Face("Kiss", R.drawable.ic_kiss));
            mListFace.add(new Face("love", R.drawable.ic_love));
            mListFace.add(new Face("Oh", R.drawable.ic_oh));
        }

        mRcvFace = (RecyclerView) findViewById(R.id.rcv_face);
        mRcvFace.setHasFixedSize(true);
//        mRcvFace.setLayoutManager(new LinearLayoutManager(this));
        mRcvFace.setLayoutManager(new GridLayoutManager(this, 3));

        mAdapter = new FaceAdapter(this, mListFace);
        mRcvFace.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, "Position: " + position, Toast.LENGTH_SHORT).show();
    }
}
