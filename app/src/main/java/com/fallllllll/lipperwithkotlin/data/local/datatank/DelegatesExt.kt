package com.fallllllll.lipperwithkotlin.data.local.datatank

import android.content.Context

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
object DelegatesExt {
    fun <T : Any> valuePreference( name:String, default:T)
    = ValuePreference(name, default)
    fun <T : Any> dataClassPreference(name:String, default:T,clazz: Class<T>)
            = DataClassPreference(name, default, clazz)
}