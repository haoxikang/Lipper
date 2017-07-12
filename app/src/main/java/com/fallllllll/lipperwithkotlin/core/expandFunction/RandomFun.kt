package com.fallllllll.lipperwithkotlin.core.expandFunction

import java.util.*

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun getRandomString(length: Int): String {
    val base = "abcdefghijklmnopqrstuvwxyz0123456789"
    val random =Random()
    val sb = StringBuilder()
    for (i in 1..length) {
        val number = random.nextInt(base.length)
        sb.append(base[number])
    }
    return sb.toString()
}