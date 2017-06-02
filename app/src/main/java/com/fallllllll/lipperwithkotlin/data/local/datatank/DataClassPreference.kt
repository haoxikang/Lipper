package com.fallllllll.lipperwithkotlin.data.local.datatank

import android.content.Context
import android.content.SharedPreferences
import com.fallllllll.AppApplication
import com.google.gson.Gson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
class DataClassPreference<T>(val name: String, val default: T, val clazz: Class<T>) : ReadWriteProperty<Any?, T> {

    val gson: Gson by lazy { AppApplication.instance.gson }

    val prefs: SharedPreferences by lazy {
        AppApplication.instance.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        return putPreference(name, value)
    }

    private fun findPreference(name: String): T = with(prefs) {
        val json: String = getString(name, "")
        if (json == "") return default else return gson.fromJson(json, clazz)
    }

    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long, is String, is Int, is Boolean, is Float -> throw IllegalArgumentException("This type can be saved into valuePreference")
            else -> putString(name, gson.toJson(value))
        }.apply()
    }

}