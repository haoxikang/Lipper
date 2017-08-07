package com.fallllllll.lipperwithkotlin.core.exception

import org.json.JSONException
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.net.ConnectException
import java.util.ArrayList

/**
 * Created by fallllllll on 2017/6/20/020.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(Parameterized::class)
class NetWorkExceptionTest {

    @Parameterized.Parameter
    lateinit var e: Throwable


    companion object{
        @JvmStatic
        @Parameterized.Parameters
        fun initData(): Collection<Throwable> {
            val throwables = ArrayList<Throwable>()

            throwables.add(JSONException("JSONException"))
            throwables.add(ConnectException("ConnectException"))
            throwables.add(NullPointerException("NullPointerException"))

            return throwables
        }
    }



    @Test
    fun testNetWorkException() {
        var apiException= NetWorkException.handleException(e)

        when(e){
            is JSONException-> assertEquals(apiException.code, PARSE_ERROR)
            is ConnectException-> assertEquals(apiException.code, NETWORK_ERROR)
            is NullPointerException-> assertEquals(apiException.code, UNKNOWN)
        }

    }

}