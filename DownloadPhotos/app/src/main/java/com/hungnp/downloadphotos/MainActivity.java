package com.hungnp.downloadphotos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by HUNG on 04/03/2017.
 */

public class MainActivity extends Activity {
    private ImageView mIvPhoto1, mIvPhoto2, mIvPhoto3, mIvPhoto4, mIvPhoto5;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mIvPhoto1 = (ImageView) findViewById(R.id.iv_photo_1);
        mIvPhoto2 = (ImageView) findViewById(R.id.iv_photo_2);
        mIvPhoto3 = (ImageView) findViewById(R.id.iv_photo_3);
        mIvPhoto4 = (ImageView) findViewById(R.id.iv_photo_4);
        mIvPhoto5 = (ImageView) findViewById(R.id.iv_photo_5);

        width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;

        Downloader downloader = new Downloader();
        downloader.execute("http://www.techrum.vn/chevereto/images/2016/04/20/9yK36.jpg",
                "http://www.techrum.vn/chevereto/images/2016/04/20/9yfmK.jpg",
                "http://www.techrum.vn/chevereto/images/2016/04/20/9y5V0.jpg",
                "http://www.techrum.vn/chevereto/images/2016/04/20/9yCqD.jpg",
                "http://www.techrum.vn/chevereto/images/2016/04/20/9ydtY.jpg");
    }

    public class Downloader extends AsyncTask<String, Void, Bitmap[]> {

        @Override
        protected Bitmap[] doInBackground(String... params) {
            Bitmap[] listBitmap = new Bitmap[5];
            listBitmap[0] = getBitMap(params[0]);
            listBitmap[1] = getBitMap(params[1]);
            listBitmap[2] = getBitMap(params[2]);
            listBitmap[3] = getBitMap(params[3]);
            listBitmap[4] = getBitMap(params[4]);
            return listBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap[] bitmaps) {
            Toast.makeText(MainActivity.this, "Waiting", Toast.LENGTH_SHORT).show();
            mIvPhoto1.setImageBitmap(bitmaps[0]);
            mIvPhoto2.setImageBitmap(bitmaps[1]);
            mIvPhoto3.setImageBitmap(bitmaps[2]);
            mIvPhoto4.setImageBitmap(bitmaps[3]);
            mIvPhoto5.setImageBitmap(bitmaps[4]);
        }

        private Bitmap getBitMap(String link) {
            try {
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                //Get scale
                float scale = (float) width / bitmap.getWidth();

                //Resize bitmap by width of screen
                bitmap = Bitmap.createScaledBitmap(bitmap, width, (int) (bitmap.getHeight() * scale), true);

                connection.disconnect();
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
