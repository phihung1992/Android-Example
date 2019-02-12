package com.app2tap.myexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMyPopupMenu();
            }
        });
    }

    private void showMyPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btnShow);
        popupMenu.getMenuInflater().inflate(R.menu.menu_demo, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_create:
                        // Do something when user click Create option
                        return true;
                    case R.id.mn_update:
                        // Do something when user click Update option
                        return true;
                    case R.id.mn_delete:
                        // Do something when user click Delete option
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}
