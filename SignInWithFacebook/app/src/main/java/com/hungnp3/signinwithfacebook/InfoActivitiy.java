package com.hungnp3.signinwithfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

public class InfoActivitiy extends AppCompatActivity {
    ProfilePictureView ppvAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView() {
        ppvAvatar = findViewById(R.id.ppv_avatar);

        Intent intent = getIntent();
        if (intent == null) return;

        String id = intent.getStringExtra("id");
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String birthday = intent.getStringExtra("birthday");

        Log.v("MY_LOG", "idid: " + id);
        Log.v("MY_LOG", "email: " + email);
        Log.v("MY_LOG", "name: " + name);
        Log.v("MY_LOG", "birthday: " + birthday);

        ppvAvatar.setProfileId(id);
    }
}
