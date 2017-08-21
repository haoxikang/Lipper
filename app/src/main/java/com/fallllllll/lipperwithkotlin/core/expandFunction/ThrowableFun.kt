package com.fallllllll.lipperwithkotlin.core.expandFunction

import com.fallllllll.lipperwithkotlin.core.exception.APIException
import com.fallllllll.lipperwithkotlin.core.exception.HTTP_ERROR

/**
 * Created by fall on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
fun Throwable.isTooManyRequest(): Boolean {
    if (this is APIException && httpExceptionCode == HTTP_ERROR && code == 423) {
        return true
    }
    return false
}

fun Throwable.isTokenOutOfDate(): Boolean {
    if (this is APIException && httpExceptionCode == HTTP_ERROR && code == 401) {
        return true
    }
    return false
}
