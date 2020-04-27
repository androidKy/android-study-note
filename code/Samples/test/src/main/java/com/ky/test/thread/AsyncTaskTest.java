package com.ky.test.thread;

import android.os.AsyncTask;
import android.os.Looper;

/**
 * description:
 * author:kyXiao
 * date:2020/4/8
 */
public class AsyncTaskTest {
    public static void main(String[] args) {
        //必须运行在Android APP中，不能单独运行在java工程，因为主线程的Looper是在APP启动的时候初始化的：ActivityThread main方法中
        //获取不到MainLooper
        System.out.println("mainLooper: " + Looper.getMainLooper());
        new AsyncTaskObj("").execute(String.valueOf(2));
        for (int i = 0; i < 5; i++) {

        }
    }

    static class AsyncTaskObj extends AsyncTask<String, Void, String> {

        public AsyncTaskObj(String param) {

        }

        @Override
        protected String doInBackground(String... strings) {
            System.out.println("doInBackground args:" + strings[0]);
            return strings[0];
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("onPostExecute result:" + s);
        }
    }
}
