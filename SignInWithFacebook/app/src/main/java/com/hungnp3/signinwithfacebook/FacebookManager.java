package com.hungnp3.signinwithfacebook;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FacebookManager {
    private static FacebookManager instance;
    private FacebookInfo mLoginInfo;

    private FacebookManager() {

    }

    public static FacebookManager getInstance() {
        if (instance == null) instance = new FacebookManager();
        return instance;
    }

    public void loadLoginInfo(final OnLoadInfoCallBack onLoadInfoCallBack) {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.v("MY_LOG", "object: " + object.toString());

                            String id = object.getString("id");
                            String email = object.getString("email");
                            String name = object.getString("name");
                            String birthday = object.getString("birthday");

                            if (birthday != null) {
                                try {
                                    Date date = new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
                                    birthday = new SimpleDateFormat("dd/MM/yyyy").format(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            mLoginInfo = new FacebookInfo(id, name, birthday, email);

                            if (onLoadInfoCallBack != null) {
                                onLoadInfoCallBack.onCompleted();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public FacebookInfo getLoginInfo() {
        return mLoginInfo;
    }

    public interface OnLoadInfoCallBack {
        void onCompleted();
    }


}
