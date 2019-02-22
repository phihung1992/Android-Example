package com.hungnp3.signinwithfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoActivitiy extends AppCompatActivity {
    private ProfilePictureView ppvAvatar;
    private TextView tvName, tvBirthday, tvEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView() {
        ppvAvatar = findViewById(R.id.ppv_avatar);
        tvName = (TextView) findViewById(R.id.tv_user_name);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        tvEmail = (TextView) findViewById(R.id.tv_email);

        Intent intent = getIntent();
        if (intent == null) return;

        String id = intent.getStringExtra("id");
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String birthday = intent.getStringExtra("birthday");

        if (birthday != null) {
            try {
                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
                birthday = new SimpleDateFormat("dd/MM/yyyy").format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        tvBirthday.setText(birthday);
        tvName.setText(name);
        tvEmail.setText(email);

        Log.v("MY_LOG", "idid: " + id);
        Log.v("MY_LOG", "email: " + email);
        Log.v("MY_LOG", "name: " + name);
        Log.v("MY_LOG", "birthday: " + birthday);

        ppvAvatar.setProfileId(id);
    }
}
