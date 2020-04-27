package com.ky.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ky.test.listview.MyListView
import com.ky.test.service.UploadImgService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyListView().layoutChildren()

        UploadImgService.startUploadImgService(this,"sdcard/upload1.jpg")
    }
}
