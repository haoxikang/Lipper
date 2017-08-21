package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.content.Context
import android.os.ResultReceiver
import android.view.inputmethod.InputMethodManager

/**
 * Created by fall on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
fun android.view.View.showIme() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    try {
        val showSoftInputUnchecked = InputMethodManager::class.java.getMethod("showSoftInputUnchecked", Int::class.java, ResultReceiver::class.java)
        showSoftInputUnchecked.isAccessible = true
        showSoftInputUnchecked.invoke(imm, 0, null)
    } catch (e: Exception) {
        //方法不见了
    }
}

fun android.view.View.hideIme() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}