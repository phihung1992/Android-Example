package com.hungnp3.demofragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFirstFragment();
    }

    private void showFirstFragment() {
        if (mFirstFragment == null) {
            mFirstFragment = new FirstFragment();
            mFirstFragment.setListener(new FirstFragment.OnCallback() {
                @Override
                public void clickButton() {
                    showSecondFragment();
                }
            });
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ln_main, mFirstFragment, FirstFragment.class.getName())
                .commit();
    }

    private void showSecondFragment() {
        if (mSecondFragment == null) {
            mSecondFragment = new SecondFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ln_main, mSecondFragment, FirstFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }
}

//    private void initFragment() {
//        mFirstFragment = new FirstFragment();
//        mFirstFragment.setListener(new FirstFragment.OnCallback() {
//            @Override
//            public void clickButton() {
//                showSecondFragment();
//            }
//        });
//        mSecondFragment = new SecondFragment();
//
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.ln_main, mFirstFragment, FirstFragment.class.getName())
//                .add(R.id.ln_main, mSecondFragment, SecondFragment.class.getName())
//                .commit();
//    }

//    private void showFirstFragment() {
//        getSupportFragmentManager().beginTransaction()
//                .show(mFirstFragment).hide(mSecondFragment)
//                .commit();
//    }
//
//    private void showSecondFragment() {
//        getSupportFragmentManager().beginTransaction()
//                .show(mSecondFragment).hide(mFirstFragment)
//                .addToBackStack(null)
//                .commit();
//    }
