package com.fall.retrofitannotation

import retrofit2.Retrofit

/**
 * Created by qqq34 on 2017/9/21.
 */
interface RetrofitBuilderFactory {
    fun  getBuilder():Retrofit.Builder
}