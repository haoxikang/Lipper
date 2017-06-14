package com.fallllllll.lipperwithkotlin.core.expandFunction

import com.fallllllll.lipperwithkotlin.core.exception.APIException
import com.fallllllll.lipperwithkotlin.core.exception.HTTP_ERROR

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun Throwable.checkTooManyRequest(): Boolean {
    if (this is APIException && httpExceptionCode == HTTP_ERROR && code == 423) {
        return true
    }
    return false
}

fun Throwable.checkToken(): Boolean {
    if (this is APIException && httpExceptionCode == HTTP_ERROR && code == 401) {
        return true
    }
    return false
}
