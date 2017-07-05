package com.fallllllll.lipperwithkotlin.data.network

import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.data.network.interceptor.LipperInterceptor
import com.fallllllll.lipperwithkotlin.data.network.interceptor.LogInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by fallllllll on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/Lipper
 */
const val DEFAULT_TIMEOUT = 15L

abstract class BaseHttpMethods<S> {

    abstract val serviceClass: Class<S>

    abstract val baseUrl: String

    val service: S by lazy { createRetrofit().create(serviceClass) }

    private fun createRetrofit(): Retrofit {
        val cacheFile = File(AppApplication.instance.externalCacheDir.toString() + "/okHttpCache", "lipper")
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(cacheFile, cacheSize)

        val localBuilder = OkHttpClient.Builder()
        localBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        return Retrofit.Builder().client(localBuilder
                .cache(cache)
                .addNetworkInterceptor(LipperInterceptor())
                .addNetworkInterceptor(LogInterceptor())
                .build())
                .addConverterFactory(getConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    open fun getConverterFactory(): Converter.Factory = GsonConverterFactory.create(AppApplication.instance.gson)

}