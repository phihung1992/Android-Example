package com.hungnp3.signinwithfacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

public class InfoActivitiy extends AppCompatActivity {
    private ProfilePictureView ppvAvatar;
    private TextView tvName, tvBirthday, tvEmail;
    private LoginButton btnLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView() {
        ppvAvatar = findViewById(R.id.ppv_avatar);
        tvName = findViewById(R.id.tv_user_name);
        tvBirthday = findViewById(R.id.tv_birthday);
        tvEmail = findViewById(R.id.tv_email);

        FacebookManager.getInstance().loadLoginInfo(new FacebookManager.OnLoadInfoCallBack() {
            @Override
            public void onCompleted() {
                FacebookInfo facebookInfo = FacebookManager.getInstance().getLoginInfo();

                String id = facebookInfo.getId();
                String email = facebookInfo.getEmail();
                String name = facebookInfo.getName();
                String birthday = facebookInfo.getDateOfBirth();

                tvBirthday.setText(birthday);
                tvName.setText(name);
                tvEmail.setText(email);

                Log.v("MY_LOG", "idid: " + id);
                Log.v("MY_LOG", "email: " + email);
                Log.v("MY_LOG", "name: " + name);
                Log.v("MY_LOG", "birthday: " + birthday);

                ppvAvatar.setProfileId(id);
            }
        });


        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                Log.d("MY_LOG", "onCurrentAccessTokenChanged()");
                Log.d("MY_LOG", "accessToken: " + accessToken);
                Log.d("MY_LOG", "accessToken2: " + accessToken2);
                if (accessToken == null) {
                    // Log in Logic
                } else if (accessToken2 == null) {
                    // Log out logic
                    startActivity(new Intent(InfoActivitiy.this, MainActivity.class));
                    finish();
                }
            }
        };
        accessTokenTracker.startTracking();
    }
}
