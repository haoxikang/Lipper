package com.fallllllll.lipperwithkotlin.data.databean.eventBean

/**
 * Created by fall on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
data class ShotsListFilterEvent(val time: String, val sort: String, val type: String)

data class ShotsMenuLayoutEvent(val shotLayoutType: String)

data class WebLoginBackEvent(val url:String)

data class LoginEvent(val isLogin:Boolean)