package com.hungnp.clock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HUNG on 25/2/2017.
 */

public class ClockView extends ImageView {
    private Paint mPaint;
    private int mStrokeWidth;
    private int mColor;
    private int mFillColor;
//    private Date date;
//    private SimpleDateFormat mDateFormat;
//    private SimpleDateFormat mDTimeFormat;

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockView);
        mStrokeWidth = typedArray.getDimensionPixelOffset(R.styleable.ClockView_stroke_width, 5);
        mColor = typedArray.getColor(R.styleable.ClockView_stroke_color, Color.GREEN);
        mFillColor = typedArray.getColor(R.styleable.ClockView_fill_color, Color.RED);
        typedArray.recycle();

        mPaint = new Paint();

//        date = new Date();
//        mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        mDTimeFormat = new SimpleDateFormat("hh:mm:ss");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(mColor);
        RectF rect = new RectF(mStrokeWidth / 2, mStrokeWidth / 2, getHeight() - mStrokeWidth / 2, getWidth() - mStrokeWidth / 2);
        canvas.drawOval(rect, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mFillColor);
        canvas.drawOval(rect, mPaint);

        //Get width and heigh of string
        String text = "GUCCI";
        Rect bounds = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), bounds);
        int height = bounds.height();
        int width = bounds.width();
        mPaint.setTextSize(30);
        mPaint.setColor(mColor);
        canvas.drawText(text, (getWidth() - width) / 2, (getWidth() - height) / 5, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.RED);
        canvas.drawLine(getWidth() / 2, getWidth() / 2, getWidth()/4, getWidth()/4, mPaint);

        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.RED);
        canvas.drawLine(getWidth() / 2, getWidth() / 2, getWidth()/3, getWidth()/4, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(Color.WHITE);
        RectF rect2 = new RectF(getWidth() / 2 - 5, getWidth() / 2 - 5, getWidth() / 2 + 5, getWidth() - getWidth() / 2 + 5);
        canvas.drawOval(rect2, mPaint);

//        drawDate(canvas);
//        drawTime(canvas);

    }

//    private void drawTime(Canvas canvas) {
//        String strTime = mDTimeFormat.format(date);
//        mPaint.setTextSize(100);
//        canvas.drawText(strTime, 250, 550, mPaint);
//    }
//
//    private void drawDate(Canvas canvas) {
//        String strDate = mDateFormat.format(date);
//        mPaint.setTextSize(100);
//        canvas.drawText(strDate, 200, 450, mPaint);
//
//    }

}
