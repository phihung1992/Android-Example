package com.app2tap.demorecyclerview;

/**
 * Project: DemoRecyclerView2.
 * Author: HUNG CODER.
 * Created on: 28/9/2017
 */

public class Face {
    private String mName;
    private int mResId;

    public Face(String name, int resId) {
        this.mName = name;
        this.mResId = resId;
    }

    public String getName() {
        return mName;
    }

    public int getResId() {
        return mResId;
    }
}
