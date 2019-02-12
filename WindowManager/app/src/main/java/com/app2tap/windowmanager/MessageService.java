package com.app2tap.windowmanager;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Project: WindowManager.
 * Author: HUNG CODER.
 * Created on: 29/9/2017
 */

public class MessageService extends Service implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "TAG";
    private WindowManager mWindowManager;
    private MyViewGroup mViewIcon;
    private MyViewGroup mViewSms;
    private WindowManager.LayoutParams mViewIconParam; // Thông số của View
    private WindowManager.LayoutParams mViewSmsParam; // Thông số của View
    private float mStartX, mStartY;
    private int previousX;
    private int previousY;
    private static final int TYPE_ICON = 0;
    private static final int TYPE_SMS = 1;
    private int mType;
    private EditText mEdtPhone, mEdtSmsContent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initView();
        return START_STICKY;
    }

    private void initView() {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE); //Lôi windowmanager
        createIconView();
        createSmsView();
        showIcon();
    }

    private void showIcon() {
        try {
            mWindowManager.removeView(mViewSms);
        } catch (Exception e) {
        }
        mWindowManager.addView(mViewIcon, mViewIconParam);
        mType = TYPE_ICON;
    }

    private void showSms() {
        try {
            mWindowManager.removeView(mViewIcon);
        } catch (Exception e) {
        }
        mWindowManager.addView(mViewSms, mViewSmsParam);
        mType = TYPE_SMS;
    }

    private void removeView(ViewGroup view) {
        try {
            mWindowManager.removeView(view);
        } catch (Exception e) {
            Toast.makeText(this, "View is not exists", Toast.LENGTH_SHORT).show();
        }
    }

    private void createSmsView() {
        mViewSms = new MyViewGroup(this);
        //inflate icon view
        View view = View.inflate(this, R.layout.view_sms, mViewSms);
        view.findViewById(R.id.btn_exit).setOnClickListener(this);
        mViewSms.setOnTouchListener(this);
        mEdtPhone = (EditText) view.findViewById(R.id.edt_number);
        mEdtPhone.setOnClickListener(this);
        mEdtSmsContent = (EditText) view.findViewById(R.id.edt_content);
        mEdtSmsContent.setOnClickListener(this);


        mViewSmsParam = new WindowManager.LayoutParams();
        mViewSmsParam.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mViewSmsParam.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mViewSmsParam.gravity = Gravity.CENTER;
        mViewSmsParam.format = PixelFormat.TRANSLUCENT; // Lam View Group tro nen trong suot
        mViewSmsParam.type = WindowManager.LayoutParams.TYPE_PHONE; //Kieu noi cua View
        mViewSmsParam.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; //Mở rộng Window ra cả ngoài màn hình
        mViewSmsParam.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; //
        mViewSmsParam.flags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH; //Lắng nghe được khi nhấn vào vùng ngoai
//         WindowManager (phan trong suot). MotionEvent.ACTION_OUTSIZE
    }

    private void createIconView() {
        mViewIcon = new MyViewGroup(this);

        //inflate icon view
        View view = View.inflate(this, R.layout.view_icon, mViewIcon);
        TextView tvIcon = (TextView) view.findViewById(R.id.tv_icon);
        tvIcon.setOnClickListener(this);
        tvIcon.setOnTouchListener(this);

        mViewIconParam = new WindowManager.LayoutParams();
        mViewIconParam.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mViewIconParam.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mViewIconParam.gravity = Gravity.CENTER;
        mViewIconParam.format = PixelFormat.TRANSLUCENT; // Lam View Group tro nen trong suot
        mViewIconParam.type = WindowManager.LayoutParams.TYPE_PHONE; //Kieu noi cua View
//        mViewIconParam.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; //Mở rộng Window ra cả ngoài màn hình
        mViewIconParam.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mViewIconParam.flags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH; //Lắng nghe được khi nhấn vào vùng ngoai
//         WindowManager (phan trong suot). MotionEvent.ACTION_OUTSIZE
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_icon:
                showSms();
                break;
            case R.id.btn_exit:
                stopSelf();
                removeView(mViewSms);
                break;
            case R.id.edt_number:
            case R.id.edt_content:
                setFocus(true);
                v.requestFocus();
                break;
            default:
                break;
        }
    }

    public void setFocus(boolean isFocus) {
        if (isFocus) {
            mViewSmsParam.flags &= ~WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        } else {
            mViewSmsParam.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }
        mWindowManager.updateViewLayout(mViewSms, mViewSmsParam);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // Nhan xuong
                if (mType == TYPE_ICON) {
                    previousX = mViewIconParam.x;
                    previousY = mViewIconParam.y;
                } else {
                    previousX = mViewSmsParam.x;
                    previousY = mViewSmsParam.y;
                }
                mStartX = event.getRawX();
                mStartY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE: // Di chuyen
                double deltaX = event.getRawX() - mStartX;
                double deltaY = event.getRawY() - mStartY;
                if (mType == TYPE_ICON) {
                    mViewIconParam.x = previousX + (int) deltaX;
                    mViewIconParam.y = previousY + (int) deltaY;
                    mWindowManager.updateViewLayout(mViewIcon, mViewIconParam);
                } else {
                    mViewSmsParam.x = previousX + (int) deltaX;
                    mViewSmsParam.y = previousY + (int) deltaY;
                    mWindowManager.updateViewLayout(mViewSms, mViewSmsParam);
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                if (mType == TYPE_SMS) {
                    showIcon();
                }
                break;
        }
        return false;
    }
}
