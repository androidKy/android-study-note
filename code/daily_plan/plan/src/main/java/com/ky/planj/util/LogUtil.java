package com.ky.planj.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * description:日志输出工具类
 * <p>
 * 注意：{@link #buildMsg(String, String)}的调用相对于此类的层级不能存在多个，不然会获取不到,
 * 要是修改了层级，一定要将trace[]的遍历层级调整到对应位置
 * <p>
 * author: diff
 * date: 2018/3/14.
 */
public class LogUtil {
    private static final String LOG_TAG = "Batmobi";

    private LogUtil(){}

    public static void out(String tag, String msg, Throwable throwable) {
        Log.i(LOG_TAG, buildMsg(tag, msg), throwable);
    }

    public static void out(String tag, String msg) {
        out(tag, msg, null);
    }

    public static void out(String tag, Throwable throwable) {
        out(tag, null, throwable);
    }

    public static void out(String msg) {
        out(null, msg, null);
    }

    public static void out(Throwable throwable) {
        out(null, null, throwable);
    }

    public static void out() {
        out(null, null, null);
    }

    public static void error(String tag, String msg, Throwable throwable) {
        Log.e(LOG_TAG, buildMsg(tag, msg), throwable);
    }

    public static void error(String tag, String msg) {
        error(tag, msg, null);
    }

    public static void error(String tag, Throwable throwable) {
        error(tag, null, throwable);
    }

    public static void error(String msg) {
        error(null, msg, null);
    }

    public static void error(Throwable throwable) {
        error(null, null, throwable);
    }

    public static void error() {
        error(null, null, null);
    }

    public static void out(String msg, int level) {
        out(LOG_TAG, msg, null, level);
    }

    public static void out(String msg, Throwable tr, int level) {
        out(LOG_TAG, msg, null, level);
    }

    public static void out(String tag, String msg, int level) {
        out(tag, msg, null, level);
    }

    private static void out(String tag, String msg, Throwable tr, int level) {
            switch (level) {
                case Log.VERBOSE:
                    Log.v(tag, msg, tr);
                    break;
                case Log.DEBUG:
                    Log.d(tag, msg, tr);
                    break;
                case Log.INFO:
                    Log.i(tag, msg, tr);
                    break;
                case Log.WARN:
                    Log.w(tag, msg, tr);
                    break;
                case Log.ERROR:
                    Log.e(tag, msg, tr);
                    break;
                case Log.ASSERT:
                    Log.wtf(tag, msg, tr);
                    break;
                default:
                    Log.i(tag, msg, tr);
                    break;
            }
            // writeFile(msg);
    }

    /**
     * 将日志格式化输出，并获取调用出处
     *
     * @param tag
     * @param msg
     * @return
     */
    private static String buildMsg(String tag, String msg) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        for (int i = 3; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
                if (!TextUtils.isEmpty(tag) && tag.equals(callingClass)) {
                    caller = String.format(".%s", trace[i].getMethodName());
                } else {
                    caller = String.format("%s.%s", callingClass, trace[i].getMethodName());
                }
                break;
            }
        }
        return String.format("[%s][%s][%s]:%s", Thread.currentThread().getId(), tag == null ? "" : tag, caller, msg == null ? "" : msg);
    }
}
