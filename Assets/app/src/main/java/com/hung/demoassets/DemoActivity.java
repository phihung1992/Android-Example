package com.hung.demoassets;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HUNG on 12/5/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private ImageView ivDemo;
    private Button btnGetData;
    private TextView tvDemo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        ivDemo = (ImageView) findViewById(R.id.iv_demo);
        btnGetData = (Button) findViewById(R.id.btn_get_data);
        tvDemo = (TextView) findViewById(R.id.tv_demo);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromAssets();
            }
        });
    }

    private void getDataFromAssets() {
        AssetManager assetManager = getAssets();

        try {
            InputStream input = assetManager.open("ic_hocap.png");
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            ivDemo.setImageBitmap(bitmap);
            input.close();

            input = assetManager.open("demo.txt");
            String result = "";
            int len;
            byte buff[] = new byte[1024];
            while ((len = input.read(buff)) > 0) {
                result += new String(buff, 0, len);
            }
            input.close();
            tvDemo.setText(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
