package com.fallllllll.lipperwithkotlin.data.local.datatank

import com.fallllllll.AppApplication
import org.jetbrains.anko.doAsync
import java.lang.reflect.Type

/**
 * Created by qqq34 on 2017/9/27.
 */
class ObjectTanker<T>(objectKye: String ) {
    private val gson by lazy { AppApplication.instance.gson }
    private var objectJson: String by DelegatesExt.valuePreference(objectKye, "")

    fun create(kClass: Class<T>): T? {
        return gson.fromJson(objectJson, kClass)
    }
    fun create(type:Type):T?{
        return gson.fromJson(objectJson,type)
    }

  fun update(t: T) {
        doAsync {
            objectJson = gson.toJson(t)
        }
    }
}