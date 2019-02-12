package com.phongbm.fingerpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PaintView extends View {
    private static final int COLOR_DEFAULT = Color.BLACK;
    private static final float STROKE_WIDTH_DEFAULT = 16F;

    private Paint paint;
    private int color;
    private float strokeWidth;

    private Path path;
    private ArrayList<MyPath> myPaths;
    private float xTouch;
    private float yTouch;

    public PaintView(Context context) {
        super(context);
        setup();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private void setup() {
        color = COLOR_DEFAULT;
        strokeWidth = STROKE_WIDTH_DEFAULT;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        // paint.setAlpha();

        myPaths = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        canvas.drawColor(Color.WHITE);
        for (MyPath myPath : myPaths) {
            paint.setColor(myPath.getColor());
            canvas.drawPath(myPath.getPath(), paint);
        }

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                path.moveTo(x, y);

                MyPath myPath = new MyPath();
                myPath.setPath(path);
                myPath.setColor(color);
                myPaths.add(myPath);

                xTouch = x;
                yTouch = y;

                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                float dX = Math.abs(x - xTouch);
                float dY = Math.abs(y - yTouch);
                if (dX >= 4F || dY >= 4) {
                    path.quadTo(xTouch, yTouch, x, y);

                    xTouch = x;
                    yTouch = y;

                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                path.lineTo(x, y);

                invalidate();
                break;

            default:
                break;
        }

        return true;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }

}