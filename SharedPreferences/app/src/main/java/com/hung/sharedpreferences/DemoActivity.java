package com.hung.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by HUNG on 22/5/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        // Get shared preferences
        pref = this.getSharedPreferences("my_intro", Context.MODE_PRIVATE);
        checkFirstTime();
    }

    private void checkFirstTime() {
        SharedPreferences.Editor editor = pref.edit();
        boolean isFirst = pref.getBoolean("is_first", true);
        if (isFirst) {
            showIntroduction();
            editor.putBoolean("is_first", false);
            editor.apply();
        }
    }

    private void showIntroduction() {
        // Show introduction in the first time of application
    }
}

     // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);

        // create menu items;
        String titles[] = { "Home", "Profile", "Calendar", "Settings" };
        int icon[] = { R.drawable.icon_home, R.drawable.icon_profile, R.drawable.icon_calendar, R.drawable.icon_settings };

        for (int i = 0; i < titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            item.setOnClickListener(this);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }