package com.hungnp3.signinwithfacebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InfoActivitiy extends AppCompatActivity implements View.OnClickListener {
    private static final int SELECT_IMAGE = 0;
    private static final int SELECT_VIDEO = 1;
    private ProfilePictureView ppvAvatar;
    private TextView tvName, tvBirthday, tvEmail;
    private LoginButton btnLogout;
    private ShareDialog shareDialog;
    private ShareLinkContent shareLinkContent;

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

        shareDialog = new ShareDialog(this);

        findViewById(R.id.btn_share_link).setOnClickListener(this);
        findViewById(R.id.btn_share_img).setOnClickListener(this);
        findViewById(R.id.btn_share_gallery_img).setOnClickListener(this);
        findViewById(R.id.btn_share_video).setOnClickListener(this);

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
                if (accessToken2 == null) {
                    // Log out logic
                    startActivity(new Intent(InfoActivitiy.this, MainActivity.class));
                    finish();
                }
            }
        };
        accessTokenTracker.startTracking();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share_link:
                shareLink();
                break;
            case R.id.btn_share_img:
                shareImage();
                break;
            case R.id.btn_share_gallery_img:
                shareGalleryImage();
                break;
            case R.id.btn_share_video:
                shareVideo();
                break;
        }
    }

    private void shareVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, SELECT_VIDEO);
    }

    private void shareGalleryImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == SELECT_IMAGE) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(sharePhotoContent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == SELECT_VIDEO) {
            Uri uri = data.getData();
            ShareVideo shareVideo = new ShareVideo.Builder()
                    .setLocalUrl(uri)
                    .build();
            ShareVideoContent shareVideoContent = new ShareVideoContent.Builder()
                    .setVideo(shareVideo)
                    .build();
            shareDialog.show(shareVideoContent);
        }
    }

    private void shareImage() {
        // Image from resource
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.test_img);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        List<SharePhoto> listPhotos = new ArrayList<>();
        listPhotos.add(photo);
        listPhotos.add(photo);
        listPhotos.add(photo);
        listPhotos.add(photo);
        listPhotos.add(photo);
        listPhotos.add(photo);
        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                .addPhotos(listPhotos)
                .build();
        shareDialog.show(sharePhotoContent);
    }

    private void shareLink() {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            shareLinkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://dantri.com.vn/xa-hoi/thu-tuong-yeu-cau-trien-khai-thi-hanh-luat-bao-ve-bi-mat-nha-nuoc-20190222081241411.htm"))
                    .setQuote("Test quote")
                    .build();
            shareDialog.show(shareLinkContent);
        }
    }
}
