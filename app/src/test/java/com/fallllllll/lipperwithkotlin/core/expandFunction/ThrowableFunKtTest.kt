package com.fallllllll.lipperwithkotlin.core.expandFunction

import com.fallllllll.lipperwithkotlin.core.exception.APIException
import com.fallllllll.lipperwithkotlin.core.exception.HTTP_ERROR
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by fallllllll on 2017/6/21/021.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ThrowableFunKtTest {
    @Test
    fun checkTooManyRequest() {
        val  apiException = APIException(Throwable(),423)
        apiException.httpExceptionCode = HTTP_ERROR
        assertTrue(apiException.checkTooManyRequest())
    }

    @Test
    fun checkToken() {
        val  apiException = APIException(Throwable(),401)
        apiException.httpExceptionCode = HTTP_ERROR
        assertTrue(apiException.checkToken())
    }

}