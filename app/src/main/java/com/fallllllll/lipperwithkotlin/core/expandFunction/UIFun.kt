package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.app.Activity

/**
 * Created by fallllllll on 2017/6/12/012.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun android.app.Activity.getStatusBarHeight():Int{
    var  statusBarHeight = -1
    val  resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId>0){
        statusBarHeight = resources.getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}
fun android.app.Activity.getNavigationBarHeight():Int{
    val resources = resources
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    val height = resources.getDimensionPixelSize(resourceId)
    return height
}