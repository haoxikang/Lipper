package com.fallllllll.lipperwithkotlin.core

import android.app.Activity
import android.content.Context
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.DIALOG_ERROR_SHOW_TIME
import com.tapadoo.alerter.Alert
import com.tapadoo.alerter.Alerter
import org.jetbrains.anko.toast

/**
 * Created by fallllllll on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/Lipper
 */
class BaseViewUtils(val context: Context) {
    private var alert: Alert? = null


    fun showToast(s: String) {
        context.toast(s)

    }

    fun showErrorDialog(s: String) {
        hideAllTopDialog()
        alert = Alerter.create(context as Activity)
                .setText(s)
                .setBackgroundColor(R.color.accent)
                .setDuration(DIALOG_ERROR_SHOW_TIME)
                .show()
    }

    fun showTopDialog(s: String) {
        hideAllTopDialog()
        alert = Alerter.create(context as Activity)
                .setText(s)
                .setDuration(Long.MAX_VALUE)
                .setBackgroundColor(R.color.primary)
                .setOnClickListener {}
                .show()
    }

    fun hideAllTopDialog() {

        if (alert != null && (alert as Alert).isShown) {
            (alert as Alert).hide()
        }

    }
}