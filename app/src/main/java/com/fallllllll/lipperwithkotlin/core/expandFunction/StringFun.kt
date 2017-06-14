package com.fallllllll.lipperwithkotlin.core.expandFunction

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun String.numberToK(string: String): String {
    var s = string
    if (!string.isEmpty() && android.text.TextUtils.isDigitsOnly(string)) {
        val a = Integer.parseInt(string)
        if (a > 999) {
            s = (a / 1000).toString() + "k"
        }
    }
    return s
}

