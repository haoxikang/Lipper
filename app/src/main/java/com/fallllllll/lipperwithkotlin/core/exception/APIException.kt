package com.fallllllll.lipperwithkotlin.core.exception

/**
 * Created by fall on 2017/5/31/031.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class APIException(throwable: Throwable, val code: Int) : Exception(throwable) {
    var httpExceptionCode = -1
}