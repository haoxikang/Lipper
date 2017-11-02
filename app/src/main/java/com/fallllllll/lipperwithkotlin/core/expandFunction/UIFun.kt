package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View

/**
 * Created by fall on 2017/6/12/012.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
fun Activity.getStatusBarHeight(): Int {
    var statusBarHeight = -1
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        statusBarHeight = resources.getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}

fun Activity.getNavigationBarHeight(): Int {
    val resources = resources
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    val height = resources.getDimensionPixelSize(resourceId)
    return height
}

fun AppCompatActivity.showSnackBar(s: String, view: View, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, s, duration).show()
}

fun Fragment.showSnackBar(s: String, view: View, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, s, duration).show()
}

fun Context.dpToPx(dp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
