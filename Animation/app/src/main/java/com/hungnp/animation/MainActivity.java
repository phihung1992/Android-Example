package com.hungnp.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by HUNG on 25/2/2017.
 */

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6, ivCard7, ivCard8, ivCard9, ivCardJack, ivCardQueen, ivCardKing;
    private TextView tvHi, tvReady, tvPray, tvResult;
    private RelativeLayout relativeLayout;
    private Button buttonPredict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        doAnimationTurn1();

    }

    private void initView() {
        relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);
        tvHi = (TextView) findViewById(R.id.tv_hi);
        tvReady = (TextView) findViewById(R.id.tv_ready);
        tvPray = (TextView) findViewById(R.id.tv_pray);
        tvResult = (TextView) findViewById(R.id.tv_result);
        ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
        ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
        ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
        ivCard4 = (ImageView) findViewById(R.id.iv_card_4);
        ivCard5 = (ImageView) findViewById(R.id.iv_card_5);
        ivCard6 = (ImageView) findViewById(R.id.iv_card_6);
        ivCard7 = (ImageView) findViewById(R.id.iv_card_7);
        ivCard8 = (ImageView) findViewById(R.id.iv_card_8);
        ivCard9 = (ImageView) findViewById(R.id.iv_card_9);
        ivCardJack = (ImageView) findViewById(R.id.iv_card_jack);
        ivCardQueen = (ImageView) findViewById(R.id.iv_card_queen);
        ivCardKing = (ImageView) findViewById(R.id.iv_card_king);
        buttonPredict = (Button) findViewById(R.id.btn_predict);
        buttonPredict.setOnClickListener(this);
    }

    private void doAnimation(final View v, int animat) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), animat);

        switch (v.getId()) {
            case R.id.tv_hi:
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        doAnimationTurn2();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case R.id.tv_ready:
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        doAnimationTurn3();
                        tvPray.setVisibility(View.VISIBLE);
                        tvResult.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                break;
            case R.id.iv_card_7:
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ivCardJack.setVisibility(View.VISIBLE);
                        ivCardQueen.setVisibility(View.VISIBLE);
                        ivCardKing.setVisibility(View.VISIBLE);
                        doAnimationTurn4();
                        buttonPredict.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            default:
                break;
        }

        v.startAnimation(animation);
    }

    private void doAnimationTurn1() {
        Animation mainAnimation = AnimationUtils.loadAnimation(this, R.anim.main_animation);
        relativeLayout.startAnimation(mainAnimation);

        doAnimation(tvHi, R.anim.anim_hi);
        doAnimation(ivCard1, R.anim.anim_card_1);
        doAnimation(ivCard2, R.anim.anim_card_2);
        doAnimation(ivCard3, R.anim.anim_card_3);
    }

    private void doAnimationTurn2() {
        doAnimation(tvReady, R.anim.anim_ready);
        doAnimation(ivCard4, R.anim.anim_card_4);
        doAnimation(ivCard5, R.anim.anim_card_5);
        doAnimation(ivCard6, R.anim.anim_card_6);
    }

    private void doAnimationTurn3() {
        doAnimation(tvPray, R.anim.anim_pray);
        doAnimation(tvResult, R.anim.anim_result);
        doAnimation(ivCard7, R.anim.anim_card_7);
        doAnimation(ivCard8, R.anim.anim_card_8);
        doAnimation(ivCard9, R.anim.anim_card_9);
    }

    private void doAnimationTurn4() {
        doAnimation(ivCardJack, R.anim.anim_card_jack);
        doAnimation(ivCardQueen, R.anim.anim_card_queen);
        doAnimation(ivCardKing, R.anim.anim_card_king);
        doAnimation(buttonPredict, R.anim.anim_button);
    }

    @Override
    public void onClick(View v) {

    }
}
