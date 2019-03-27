package com.hungnp3.customview;

public class Face {
    private String mColor;
    private int faceId;
    private String name;

    public Face(String mColor, int faceId, String name) {
        this.mColor = mColor;
        this.faceId = faceId;
        this.name = name;
    }

    public String getColor() {
        return mColor;
    }

    public int getFaceId() {
        return faceId;
    }

    public String getName() {
        return name;
    }
}