package com.hungnp3.signinwithfacebook;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    CallbackManager mCallbackManager;
    LoginButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AccessToken token;
        token = AccessToken.getCurrentAccessToken();

        if (token == null) {
            setContentView(R.layout.activity_main);

//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.hungnp3.signinwithfacebook",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (Exception e) {
//
//        }


//            keytool -exportcert -alias key0 -keystore debug_key.jks | C:\openssl\bin\openssl sha1 -binary | C:\openssl\bin\openssl base64
            
            initView();
        } else {
            Intent intent = new Intent(MainActivity.this, InfoActivitiy.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        mCallbackManager = CallbackManager.Factory.create();

        btnLogin = findViewById(R.id.btn_login_facebook);
        btnLogin.setReadPermissions("public_profile", "email", "user_birthday", "user_friends");
        btnLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("MY_LOG", "onSuccess");

                Intent intent = new Intent(MainActivity.this, InfoActivitiy.class);

//                intent.putExtra("id", id);
//                intent.putExtra("name", name);
//                intent.putExtra("birthday", birthday);
//                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                Log.d("MY_LOG", "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("MY_LOG", "onError");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        LoginManager.getInstance().logOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
