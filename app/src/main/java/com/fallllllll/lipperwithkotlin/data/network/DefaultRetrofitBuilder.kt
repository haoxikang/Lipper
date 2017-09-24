package com.fallllllll.lipperwithkotlin.data.network

import com.fall.retrofitannotation.DefaultBuilder
import com.fall.retrofitannotation.RetrofitBuilderFactory
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
 * Created by qqq34 on 2017/9/21.
 */
const val DEFAULT_TIMEOUT = 15L

@DefaultBuilder
open class DefaultRetrofitBuilder : RetrofitBuilderFactory {
    override fun getBuilder(): Retrofit.Builder {
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

    }

    open fun getConverterFactory(): Converter.Factory = GsonConverterFactory.create(AppApplication.instance.gson)

}