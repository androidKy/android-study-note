package com.ky.demo.listview;

import android.util.Log;

/**
 * description:
 * author:kyXiao
 * date:2019/5/8
 */
public class MyListView extends MyAbsListView {
    private static final String TAG = "MyListView";

    @Override
    protected void layoutChildren() {
        Log.i(TAG, "layoutChildren: ");
    }
}
