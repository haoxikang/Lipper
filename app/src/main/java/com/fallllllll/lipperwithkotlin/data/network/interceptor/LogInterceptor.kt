package com.fallllllll.lipperwithkotlin.data.network.interceptor

import com.fallllllll.lipperwithkotlin.utils.LogUtils
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body()?.contentType()
        val content = response.body()?.string()
        LogUtils.d("\n")
        LogUtils.d("---------------Start----------------")
        LogUtils.d("|" + request.toString())
        val method = request.method()
        if ("POST" == method) {
            val sb = StringBuilder()
            if (request.body() is FormBody) {
                val body = request.body() as FormBody
                for (i in 0..body.size()) {
                    sb.append(
                            body.encodedName(i)
                    ).append("=")
                            .append(body.encodedValue(i)).append(",")
                }
                sb.delete(sb.length - 1, sb.length)
                LogUtils.d("| RequestParams:{" + sb.toString() + "}")
            }
        }
        LogUtils.d("| Request Code :" + response.code())
        LogUtils.d("| Response:" + content)
        LogUtils.d("----------End:" + duration + "毫秒----------")
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build()
    }

}