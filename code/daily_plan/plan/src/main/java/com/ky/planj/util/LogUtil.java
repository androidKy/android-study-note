package com.ky.planj.util;

import android.util.Log;

import com.ky.planj.BuildConfig;

/**
 * description: 日志打印工具
 * author: kyXiao
 * date: 2018/11/15
 */
public class LogUtil {
    private static final String TAG = "APPLICATION_TAG";
    private static final boolean DEBUG = true;

    public static void info(String msg) {
        info(TAG, msg);
    }

    public static void info(String tag, String msg) {
        info(tag, msg, null);
    }

    public static void info(String tag, String msg, Throwable throwable) {
        if (DEBUG)
            Log.i(tag, msg, throwable);
    }

    public static void info(Throwable throwable) {
        if (throwable != null)
            info(TAG, throwable.getMessage(), throwable);
    }

    public static void error(Throwable throwable) {
        if (throwable != null)
            error(TAG, throwable.getMessage(), throwable);
    }

    public static void error(String msg) {
        error(TAG, msg);
    }

    public static void error(String tag, String msg) {
        error(tag, msg, null);
    }

    public static void error(String tag, String msg, Throwable throwable) {
        if (DEBUG)
            Log.e(tag, msg, throwable);
    }
}
