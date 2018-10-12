package com.ky.demo;

import android.app.Application;
import android.util.Log;

/**
 * description:应用程序
 * author: kyXiao
 * created date: 2018/10/12 10:18
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }
}
