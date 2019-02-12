package com.app2tap.levellist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivPhoto;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        ivPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_photo:
                ivPhoto.setImageLevel(count);
                count++;
                if (count >= 5) {
                    count = 0;
                }
                break;
            default:
                break;
        }
    }
}
