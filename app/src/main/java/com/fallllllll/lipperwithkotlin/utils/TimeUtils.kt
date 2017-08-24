package com.fallllllll.lipperwithkotlin.utils

import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by fall on 2017/7/14/014.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
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

