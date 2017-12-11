package com.fallllllll.lipperwithkotlin.utils

import android.content.res.ColorStateList
import android.text.TextPaint
import android.text.style.URLSpan

/**
 * Created by 康颢曦 on 2017/12/11.
 */
class TouchableUrlSpan(val url:String,textColor:ColorStateList,val pressedBackgroundColor:Int):URLSpan(url){
    private var isPressed=false
    private val normalTextColor=textColor.defaultColor
    private val pressedTextColor=textColor.getColorForState(intArrayOf(android.R.attr.state_pressed),normalTextColor)
    fun seetPressed(isPressed:Boolean){
        this.isPressed=isPressed
    }

    override fun updateDrawState(ds: TextPaint?) {
        ds?.let {
            it.color = if (isPressed)pressedTextColor else normalTextColor
            it.bgColor = if (isPressed)pressedBackgroundColor else 0
            it.isUnderlineText=!isPressed
        }
    }
}