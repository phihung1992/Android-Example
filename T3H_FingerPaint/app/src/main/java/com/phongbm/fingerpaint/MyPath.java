package com.phongbm.fingerpaint;

import android.graphics.Path;

public class MyPath {
    private Path path;
    private int color;

    public MyPath() {
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}