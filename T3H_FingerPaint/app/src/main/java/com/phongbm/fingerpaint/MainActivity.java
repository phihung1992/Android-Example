package com.phongbm.fingerpaint;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btnSave;
    private Button btnEraser;
    private Button btnChangeColorRed;
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnEraser = findViewById(R.id.btnEraser);
        btnChangeColorRed = findViewById(R.id.btnChangeColorRed);
        paintView = findViewById(R.id.paintView);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setDrawingCacheEnabled(true);
                paintView.buildDrawingCache();
                Bitmap bitmap = paintView.getDrawingCache();

                String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/" +
                        System.currentTimeMillis()+ ".jpg";
                File file = new File(path);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                    bitmap.recycle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setColor(Color.WHITE);
            }
        });

        btnChangeColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setColor(Color.RED);
            }
        });
    }

}