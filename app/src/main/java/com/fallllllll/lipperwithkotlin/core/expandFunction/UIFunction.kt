package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by fallllllll on 2017/5/31/031.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun AppCompatActivity.showSnackBar(s:String,view: View,duration:Int=Snackbar.LENGTH_SHORT){
    Snackbar.make(view,s,duration).show()
}
fun Fragment.showSnackBar(s:String,view: View,duration:Int=Snackbar.LENGTH_SHORT){
    Snackbar.make(view,s,duration).show()
}