package com.ky.test.listview;

import android.util.Log;

/**
 * description:
 * author:kyXiao
 * date:2019/5/8
 */
public class MyListView extends MyAbsListView {
    private static final String TAG = "MyListView";

    @Override
    public void layoutChildren() {
        //super.layoutChildren();
        Log.i(TAG, "layoutChildren: ");
    }
}
