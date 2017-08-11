package com.fallllllll.lipperwithkotlin.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by fallllllll on 2017/7/14/014.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun getTime(s: String?): String? {
    if (s.isNullOrEmpty()) {
        return s
    }
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    val tm = SimpleDateFormat("MMM d, yyyy", Locale.US)
    try {
        val data = sdf.parse(s)
        return tm.format(data)
    } catch (e: ParseException) {
        return s
    }

}