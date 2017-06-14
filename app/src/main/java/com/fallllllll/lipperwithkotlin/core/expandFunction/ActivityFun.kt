package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.view.View
import com.fallllllll.lipperwithkotlin.R

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */

/**
 * 简单型状态栏(ToolBar)
 */
fun android.app.Activity.setOrdinaryToolbar() {
    window.statusBarColor = android.support.v4.content.ContextCompat.getColor(this, com.fallllllll.lipperwithkotlin.R.color.primary_dark)
}

/**
 * 图片全屏透明状态栏（图片位于状态栏下面）
 */
fun android.app.Activity.setImageTransparent() {
    window.decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

/**
 * 图片全屏半透明状态栏（图片位于状态栏下面）
 */
fun android.app.Activity.setImageTranslucent() {
    window.decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    window.statusBarColor = android.support.v4.content.ContextCompat.getColor(this, com.fallllllll.lipperwithkotlin.R.color.statusBar)
}
