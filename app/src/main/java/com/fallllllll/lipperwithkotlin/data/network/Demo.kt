package com.fallllllll.lipperwithkotlin.data.network

import com.fallllllll.lipperwithkotlin.data.network.service.DribbbleService
import retrofit2.Retrofit

/**
 * Created by qqq34 on 2017/9/22.
 */
class Demo{

    val service:DribbbleService by lazy { createRetrofit().create(DribbbleService::class.java) }

    private fun createRetrofit():Retrofit{
        return DefaultRetrofitBuilder().getBuilder().baseUrl("idididdidi").build()
    }
}