package com.fallllllll.lipperwithkotlin.core.exception

/**
 * Created by fallllllll on 2017/5/31/031.
 * GitHub :  https://github.com/348476129/Lipper
 */
class APIException(throwable: Throwable, val code: Int) : Exception(throwable) {
    var httpExceptionCode = -1
}