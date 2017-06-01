package com.fallllllll.lipperwithkotlin.data.local.datatank

import android.content.Context

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
object DelegatesExt {
    fun <T : Any> valuePreference(context: Context, name:String, default:T)
    = ValuePreference(context, name, default)
    fun <T : Any> dataClassPreference(context: Context, name:String, default:T,clazz: Class<T>)
            = DataClassPreference(context, name, default, clazz)
}