package com.hung.myasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HUNG on 1/5/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private TextView tvNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        tvNumber = (TextView) findViewById(R.id.tv_number);
        findViewById(R.id.btn_increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsync myAsync = new MyAsync();
                myAsync.execute();
            }
        });
    }

    private class MyAsync extends AsyncTask<Void, Integer, Void> {
        private int count;

        @Override
        protected void onPreExecute() {
            count = 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (count < 10) {
                count++;
                publishProgress(count);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvNumber.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(DemoActivity.this, "Done!", Toast.LENGTH_SHORT).show();
        }
    }

}
