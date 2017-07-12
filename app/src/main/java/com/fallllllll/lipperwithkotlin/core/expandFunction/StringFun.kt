package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.text.TextUtils

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun String.numberToK(): String {
    var s = this
    if (!this.isNullOrEmpty() && TextUtils.isDigitsOnly(this)) {
        val a = Integer.parseInt(this)
        if (a > 999) {
            s = (a / 1000).toString() + "k"
        }
    }
    return s
}

