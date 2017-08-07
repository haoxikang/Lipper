package com.fallllllll.lipperwithkotlin.core.exception

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.text.ParseException

/**
 * Created by fallllllll on 2017/5/31/031.
 * GitHub :  https://github.com/348476129/Lipper
 */
class NetWorkException {
    companion object {
        fun handleException(e: Throwable): APIException {
            val apiException: APIException
            when (e) {
                is HttpException -> {
                    apiException = APIException(e, HTTP_ERROR)
                    apiException.httpExceptionCode = e.code()
                }
                is JsonParseException,
                is JSONException,
                is ParseException -> {
                    apiException = APIException(e, PARSE_ERROR)
                }
                is ConnectException -> {
                    apiException = APIException(e, NETWORK_ERROR)
                }
                else -> {
                    apiException = APIException(e, UNKNOWN)
                }
            }
            return apiException
        }
    }
}