package com.hungnp3.downloaderservice;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String photoURL = "https://developer.android.com/images/service_lifecycle.png";
        downloadFile(photoURL);
        return START_NOT_STICKY;
    }

    private void downloadFile(final String photoURL) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(photoURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);

                    String path = saveToFile(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private String saveToFile(Bitmap bitmap) {
        // Save file to Download folder in external storage.
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + Environment.DIRECTORY_DOWNLOADS + "/"
                + System.currentTimeMillis() + ".jpg";
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
