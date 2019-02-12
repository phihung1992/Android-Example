package com.hung.customtoast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by HUNG on 26/4/2017.
 */

public class MyToast extends Toast {
    private Context mContext;
    private ImageView ivIcon;
    private TextView tvContent;

    public final int ICON_LIST[] = {
            R.drawable.ic_angry,
            R.drawable.ic_cry,
            R.drawable.ic_dead,
            R.drawable.ic_embarrass,
            R.drawable.ic_happy,
            R.drawable.ic_joy,
            R.drawable.ic_love,
            R.drawable.ic_sad,
    };
    private final String NAME_LIST[] = {
            "angry",
            "cry",
            "dead",
            "embarrass",
            "happy",
            "joy",
            "love",
            "sad"
    };

    public MyToast(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView() {
        setDuration(Toast.LENGTH_SHORT);
        setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
        View view = View.inflate(mContext, R.layout.item_toast, null);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        setView(view);
    }

    @Override
    public void show() {
        Random random = new Random();
        int result = random.nextInt(NAME_LIST.length);
        ivIcon.setImageResource(ICON_LIST[result]);
        tvContent.setText(NAME_LIST[result]);

        super.show();
    }
}
