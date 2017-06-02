package com.fallllllll.lipperwithkotlin.data.local.datatank

import android.content.Context
import android.content.SharedPreferences
import com.fallllllll.AppApplication
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by fallllllll on 2017/6/1/001.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ValuePreference<T>(val name: String, val default: T) : ReadWriteProperty<Any?, T> {

    private var currentValue: T? = null

    val prefs: SharedPreferences by lazy {
        AppApplication.instance.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {

        if (currentValue == null) {
            currentValue = findPreference(name, default)
        }
        return currentValue as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        currentValue = value
        return putPreference(name, value)
    }
    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into valuePreference")
        }
        res as T

    }

    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> {
                putLong(name, value)
            }
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into valuePreference")
        }.apply()
    }

}
