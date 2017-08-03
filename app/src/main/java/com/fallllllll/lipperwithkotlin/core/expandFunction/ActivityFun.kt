package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.View
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.ui.login.DribbbleLoginActivity
import com.fallllllll.lipperwithkotlin.ui.transitions.FabTransform

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */

/**
 * 简单型状态栏(ToolBar)
 */
fun Activity.setOrdinaryToolbar() {
    window.statusBarColor =ContextCompat.getColor(this, R.color.primary_dark)
}

/**
 * 图片全屏透明状态栏（图片位于状态栏下面）
 */
fun Activity.setImageTransparent() {
    window.decorView.systemUiVisibility =View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

/**
 * 图片全屏半透明状态栏（图片位于状态栏下面）
 */
fun Activity.setImageTranslucent() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar)
}
 fun BaseActivity.goLogin(color: Int, @DrawableRes drawableId: Int, view: View) {
    val intent = Intent(this, DribbbleLoginActivity::class.java)
    FabTransform.addExtras(intent, color, drawableId)
    val options = ActivityOptions.makeSceneTransitionAnimation(
            this, view, getString(R.string.transition_dribbble_login))
    startActivity(intent, options.toBundle())
}
