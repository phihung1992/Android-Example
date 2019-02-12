package com.app2tap.customgridview;

/**
 * Project: CustomGridView
 * Author: HUNG CODER
 * Created on: 16/09/2017.
 */

public class Face {
    private String name;
    private int resId;

    public Face(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public int getResId() {
        return resId;
    }
}
