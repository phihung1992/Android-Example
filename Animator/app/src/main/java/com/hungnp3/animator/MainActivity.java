package com.hungnp3.animator;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTest;
    private TextView tvTest;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnTest = findViewById(R.id.btn_test);
        tvTest = (TextView) findViewById(R.id.tv_test);

        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                animationTextView();
                break;
        }
    }

    private void animationTextView() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 800f, 500f, 800f, 600f, 800f, 750f, 800f);
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(13, 15, 18, 20, 18, 15, 13, 5);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0f, 1f);
        valueAnimator.setInterpolator(new LinearInterpolator());
        // Nó sẽ tăng tốc độ ở lần đầu và sau đó giảm dần

        valueAnimator.setDuration(2000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                float progress = (float) animation.getAnimatedValue();
//                tvTest.setTranslationY(progress);
//                tvTest.setTranslationX(progress);
//                count++;
//                Log.d("MYLOG", String.valueOf(count));
                float value = (float) animation.getAnimatedValue();
//                tvTest.setTextSize(value);
                tvTest.setAlpha(value);
//                tvTest.setRotation(value);
            }
        });

        valueAnimator.start();
    }
}
