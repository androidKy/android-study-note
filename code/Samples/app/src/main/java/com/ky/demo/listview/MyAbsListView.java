package com.ky.demo.listview;

import android.util.Log;

/**
 * description:
 * author:kyXiao
 * date:2019/5/8
 */
public abstract class MyAbsListView {
    private static final String TAG = "MyAbsListView";

    public void onLayout() {
        layoutChildren();
    }

    protected void layoutChildren() {
        Log.i(TAG, "layoutChildren: ");
    }
}
