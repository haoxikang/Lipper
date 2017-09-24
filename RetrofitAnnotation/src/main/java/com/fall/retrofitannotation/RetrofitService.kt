package com.fall.retrofitannotation

/**
 * Created by qqq34 on 2017/9/6.
 */


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class RetrofitService(val baseUrl:String)


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class RetrofitBuilder ( val servicePaths:Array<String>)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class DefaultBuilder