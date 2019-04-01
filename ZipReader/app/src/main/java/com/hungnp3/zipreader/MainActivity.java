package com.hungnp3.zipreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ACCESS_EXTERNAL_STORAGE = 0;
    public static final String DB_PATH = Environment.getExternalStorageDirectory().getPath() + "/zipReader";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermissions();
        readZip();
//        unZip();
    }

    private void initPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_ACCESS_EXTERNAL_STORAGE);
                return;
            }
        }

        unZip();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ACCESS_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                unZip();
            }
        }
    }

    private void readZip() {
        AssetManager assetManager = getAssets();

        try {
            InputStream is = assetManager.open("2P1W.zip");
            ZipInputStream zis = new ZipInputStream(is);
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                log("File: " + ze.getName() + " Size: " + ze.getSize() + " Last Modified " + longToTime(ze.getTime()));
            }

        } catch (IOException e) {
            log("Error: " + e.getMessage());
        }
    }

    private void unZip() {
        // Tao Thu Muc
        File folder = new File(DB_PATH);
        folder.mkdir();

        byte[] buff = new byte[1024];

        try {
            InputStream input = getAssets().open("2P1W.zip");
            ZipInputStream stream = new ZipInputStream(input);

            ZipEntry entry;
            while ((entry = stream.getNextEntry()) != null) {
                File file = new File(DB_PATH + "/" + entry.getName());
                if (file.exists()) {
                    continue;
                }
                file.createNewFile();

                FileOutputStream output = new FileOutputStream(file);
                int len;

                while ((len = stream.read(buff)) > 0) {
                    output.write(buff, 0, len);
                }

                output.close();
            }

            stream.close();
            input.close();
        } catch (Exception e) {
            log("Error: " + e.getMessage());
        }
    }

    private String longToTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(new Date(time));
    }

    public void log(String msg) {
        Log.d("MYLOG", msg);
    }
}
