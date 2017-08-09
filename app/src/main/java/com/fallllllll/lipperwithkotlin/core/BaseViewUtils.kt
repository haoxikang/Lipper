package com.fallllllll.lipperwithkotlin.core

import android.content.Context
import android.support.design.widget.Snackbar
import org.jetbrains.anko.toast

/**
 * Created by fallllllll on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/Lipper
 */
class BaseViewUtils(val context: Context) {


    fun showToast(s: String) {
        context.toast(s)

    }

    fun showErrorDialog(s: String) {
        context.toast(s)
    }

    fun showTopDialog(s: String) {
        context.toast(s)
    }

    fun hideAllTopDialog() {


    }
}