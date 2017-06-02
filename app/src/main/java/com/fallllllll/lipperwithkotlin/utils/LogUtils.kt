package com.fallllllll.lipperwithkotlin.utils


import android.util.Log

/**
 * Created by fallllllll on 2017/3/16.
 */

object LogUtils {
    private val tag = "LogUtils"
    private val isCanLog = true

    fun i(s: String) {
        if (isCanLog) Log.i(tag, s)
    }

    fun w(s: String) {
        if (isCanLog) Log.w(tag, s)
    }

    fun d(s: String) {
        if (isCanLog) Log.d(tag, s)
    }

    fun e(s: String) {
        if (isCanLog) Log.e(tag, s)
    }
}