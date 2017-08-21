package com.fallllllll.lipperwithkotlin.data.local.datatank

/**
 * Created by fall on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
object DelegatesExt {
    fun <T : Any> valuePreference( name:String, default:T)
    = ValuePreference(name, default)
}