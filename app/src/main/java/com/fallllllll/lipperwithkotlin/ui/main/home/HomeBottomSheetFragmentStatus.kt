package com.fallllllll.lipperwithkotlin.ui.main.home

import android.content.Context
import com.fallllllll.lipperwithkotlin.R
import java.util.*

/**
 * Created by 康颢曦 on 2017/6/14.
 */
class HomeBottomSheetFragmentStatus(val context: Context,val time:String,val  sort:String,val  type:String) {
    val listTime: Array< String>? =context.resources.getStringArray(R.array.time)
    val listSort: Array< String>? = context.resources.getStringArray(R.array.sort)
    val listType: Array< String>? =context.resources.getStringArray(R.array.type)
}