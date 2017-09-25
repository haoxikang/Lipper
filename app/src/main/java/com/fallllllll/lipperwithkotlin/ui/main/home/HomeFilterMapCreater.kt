package com.fallllllll.lipperwithkotlin.ui.main.home

import android.content.Context
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.*

/**
 * Created by qqq34 on 2017/9/25.
 */
class HomeFilterMapCreater {
    companion object {
        private val sortMap: MutableMap<String, String> = HashMap()
        private val timeMap: MutableMap<String, String> = HashMap()
        private val typeMap: MutableMap<String, String> = HashMap()

        fun getSortMap(context: Context) =
                if (sortMap.isEmpty()) {
                    val listSortKey = arrayOf(POPULARITY, RECENT, VIEWS, COMENTS)
                    val listSortValue = context.resources.getStringArray(R.array.sort)
                    for (i in 0 until listSortValue.size) {
                        sortMap.put(listSortKey[i], listSortValue[i])
                    }
                    sortMap.toMap()
                } else {
                    sortMap.toMap()
                }

        fun getTimeMap(context: Context) =
                if (timeMap.isEmpty()) {
                    val listTimeKey = arrayOf(NOW, WEEK, MONTH, YEAR, EVER)
                    val listTimeValue = context.resources.getStringArray(R.array.time)
                    for (i in 0 until listTimeValue.size) {
                        timeMap.put(listTimeKey[i], listTimeValue[i])
                    }
                    timeMap.toMap()
                } else {
                    timeMap.toMap()
                }

        fun getTypeMap(context: Context) =
                if (typeMap.isEmpty()) {
                    val listTypeKey = arrayOf(SHOTS, DEBUTS, TEAMS, PLAYOFFS, ANIMATED, ATTACHMENTS, REBOUNDS)
                    val listTypeValue = context.resources.getStringArray(R.array.type)
                    for (i in 0 until listTypeValue.size) {
                        typeMap.put(listTypeKey[i], listTypeValue[i])
                    }
                    typeMap.toMap()
                } else {
                    typeMap.toMap()
                }


    }


}