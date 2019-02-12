package com.app2tap.applicationcontext;

import android.app.Application;

/**
 * Example: ApplicationContext.
 * Author: HUNG CODER.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
