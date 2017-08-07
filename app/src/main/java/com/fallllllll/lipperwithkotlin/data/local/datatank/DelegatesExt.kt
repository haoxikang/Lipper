package com.fallllllll.lipperwithkotlin.data.local.datatank

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
object DelegatesExt {
    fun <T : Any> valuePreference( name:String, default:T)
    = ValuePreference(name, default)
}