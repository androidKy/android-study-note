package com.ky.demo;

import android.app.backup.BackupAgent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

/**
 * description:
 * author: kyXiao
 * created date: 2018/9/7
 */

public class LooperActivity extends AppCompatActivity {
    private static final String TAG = "LooperActivity";

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        looperTest();

        ButterKnife.bind(this);
    }

    private void looperTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Log.i(TAG, "looper 的范围内");

                Handler handler = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.i(TAG, "handleMessage: " + msg.what);
                    }
                };
                Message msg = Message.obtain();

                handler.sendMessage(msg);
                Looper.loop();

                Log.i(TAG, "looper 的范围外");
            }
        }).start();
    }
}
