package com.ky.plan.uitl

import android.text.TextUtils
import android.util.Log

/**
 * description: 日志打印工具类
 * author: kyXiao
 * date: 2018/11/7
 */
class LogUtil {
    private constructor()

    companion object {
        private val LOG_TAG = "ky_plan"
        private var DEBUG = true

        public fun printI(tag: String, message: String) {
            printI(LOG_TAG, message, null)
        }

        public fun printI(tag: String, message: String, exception: Throwable?) {
            printI(tag, buildMsg(tag, message), exception, Log.INFO)
        }

        public fun printI(tag: String, message: String, exception: Throwable?, level: Int) {
            if (DEBUG) {
                when (level) {
                    Log.VERBOSE -> Log.v(tag, message, exception)
                    Log.INFO -> Log.i(tag, message, exception)
                    Log.DEBUG -> Log.d(tag, message, exception)
                    Log.ERROR -> Log.e(tag, message, exception)
                }
            }
        }

        private fun buildMsg(tag: String?, msg: String?): String {
            val trace = Throwable().fillInStackTrace().stackTrace
            var caller = "<unknown>"
            for (i in 3 until trace.size) {
                val clazz = trace[i].javaClass
                if (clazz != LogUtil::class.java) {
                    var callingClass = trace[i].className
                    callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1)
                    callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1)
                    if (!TextUtils.isEmpty(tag) && tag == callingClass) {
                        caller = String.format(".%s", trace[i].methodName)
                    } else {
                        caller = String.format("%s.%s", callingClass, trace[i].methodName)
                    }
                    break
                }
            }
            return String.format("[%d][%s][%s]:%s", Thread.currentThread().id, tag
                    ?: "", caller, msg
                    ?: "")
        }
    }
}