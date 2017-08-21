package com.fallllllll.lipperwithkotlin.core.presenter

import android.support.annotation.StringRes

/**
 * Created by fall on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
interface Contract {
    interface BaseView {
        fun showToast(s: String)
        fun showTopDialog(s: String)
        fun hideAllTopDialog()
        fun showErrorDialog(s: String)
        fun getString(@StringRes res: Int): String
    }

    interface Presenter {
        fun attach()

        fun detach()

        fun onPresenterCreate()
    }
}