package com.example.hung.mytextrecognition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

/**
 * Created by HUNG on 10/05/2017.
 */

public class MyActivity extends AppCompatActivity {
    private ImageView ivImage;
    private Button btnGetText;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        ivImage = (ImageView) findViewById(R.id.iv_image);
        final Bitmap bitmap = getBitmapFromImage(R.drawable.img_3);
        ivImage.setImageBitmap(bitmap);

        btnGetText = (Button) findViewById(R.id.btn_get_text);
        tvResult = (TextView) findViewById(R.id.tv_result);

        btnGetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = getTextFromImage(bitmap);
                tvResult.setText(result);
            }
        });
    }

    private String getTextFromImage(Bitmap bitmap) {
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (textRecognizer.isOperational()) {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> listItem = textRecognizer.detect(frame);

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < listItem.size(); ++i) {
                TextBlock textBlock = listItem.valueAt(i);
                builder.append(textBlock.getValue());
                builder.append("\n");
            }
            return builder.toString();
        }
        return null;
    }

    private Bitmap getBitmapFromImage(int drawableID) {
        return BitmapFactory.decodeResource(getApplicationContext().getResources(), drawableID);
    }
}
