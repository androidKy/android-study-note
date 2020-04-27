package com.ky.test.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * description:
 * author:kyXiao
 * date:2020/4/10
 */
class UploadImgService : IntentService {
    private val TAG = UploadImgService::class.java.simpleName

    //constructor():super("UploadImgService")

    constructor(name: String) : super(name){
        Log.d(TAG,"有参构造函数")
    }

    init {
        setIntentRedelivery(true)
    }

    companion object {
        private const val ACTION_UPLOAD_IMG = "com.zhy.blogcodes.intentservice.action.UPLOAD_IMAGE"

        fun startUploadImgService(context: Context, path: String) {
            Intent(context, UploadImgService::class.java).also {
                it.action = ACTION_UPLOAD_IMG
                it.putExtra("path", path)

                context.startService(it)
            }
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: action=${intent?.action} path=${intent?.getStringExtra("path")}")

        try {
            Thread.sleep(3000)
        } catch (e: Exception) {
        }
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.d(TAG, "onStart")
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }
}