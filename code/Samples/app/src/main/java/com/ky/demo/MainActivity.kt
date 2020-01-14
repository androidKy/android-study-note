package com.ky.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ky.demo.listview.MyListView
import com.ky.demo.util.AndroidUtils

class MainActivity : AppCompatActivity() {

    var varObj = "hello world"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "sample"

        AndroidUtils.getRunningProcess()

        MyListView().onLayout()
    }

    private fun getString(var1: String, var2: Int): String {
        return var1
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return super.shouldShowRequestPermissionRationale(permission)
    }
}
