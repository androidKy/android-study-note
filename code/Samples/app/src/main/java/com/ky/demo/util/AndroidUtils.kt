package com.ky.demo.util

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * description:
 * author:kyXiao
 * date:2019/5/27
 */
class AndroidUtils {
    companion object {
        private val TAG by lazy { AndroidUtils::class.java.simpleName }
        fun getRunningProcess() {
            val process = Runtime.getRuntime().exec("/system/bin/ps")
            val bufferReader = BufferedReader(InputStreamReader(process.inputStream))

            val sb = StringBuffer()

            while (bufferReader.readLine() != null) {
                sb.append(bufferReader.readLine() + "\n")
            }

            Log.i(TAG, "reader: $sb")
        }
    }

}