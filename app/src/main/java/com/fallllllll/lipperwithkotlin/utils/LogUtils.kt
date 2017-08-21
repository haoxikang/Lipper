package com.fallllllll.lipperwithkotlin.utils


import android.util.Log

/**
 * Created by fall on 2017/3/16.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
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