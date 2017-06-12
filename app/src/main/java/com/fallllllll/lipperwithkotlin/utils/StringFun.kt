package com.fallllllll.lipperwithkotlin.utils

import android.text.TextUtils

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun String.numberToK(string: String): String {
    var s = string
    if (!string.isEmpty() && TextUtils.isDigitsOnly(string)) {
        var a = string as Int
        if (a > 999) {
            s = (a / 1000).toString() + "k"
        }
    }
    return s
}

