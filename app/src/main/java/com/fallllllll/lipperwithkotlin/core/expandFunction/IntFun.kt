package com.fallllllll.lipperwithkotlin.core.expandFunction

/**
 * Created by fall on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
fun Int.numberToK(): String {
    var s: String = this.toString()

    if (this > 999) {
        s = (this / 1000).toString() + "k"
    }

    return s
}
