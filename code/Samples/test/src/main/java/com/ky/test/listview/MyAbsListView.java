package com.ky.test.listview;

import android.util.Log;

/**
 * description:
 * author:kyXiao
 * date:2019/5/8
 */
public class MyAbsListView {
    private static final String TAG = "MyListView";

    public void onLayout() {
        Log.i(TAG, "MyAbsListView onLayout: ");
        layoutChildren();
    }

    public void layoutChildren() {
        Log.i(TAG, "MyAbsListView layoutChildren: ");
    }
}
