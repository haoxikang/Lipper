package com.fallllllll.lipperwithkotlin.ui.main.home

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity.END
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.PopupWindow
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_LARGE
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_ONLY_IMAGE
import com.fallllllll.lipperwithkotlin.core.constants.SHOTS_LAYOUT_SMALL
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsMenuLayoutEvent
import kotlinx.android.synthetic.main.view_home_item_popup_window.view.*

/**
 * Created by 康颢曦 on 2017/6/18.
 */
class HomeItemLayoutPopWindow(val activity: Activity) : PopupWindow() {
    init {
        val view = LayoutInflater.from(activity).inflate(R.layout.view_home_item_popup_window, null, false)
        contentView = view
        isFocusable = true
        update()
        width = WRAP_CONTENT
        height = WRAP_CONTENT
        val dw = ColorDrawable(Color.parseColor("#00000000"))
        setBackgroundDrawable(dw)
        initListener(view)
    }

    fun showPopUpWindow(parent: View) {
        if (isShowing) showAsDropDown(parent, 0, 0, END) else dismiss()
    }

    private fun initListener(view: View) {
        view.homePopupLargeLayout.setOnClickListener {
            RxBus.get().post(ShotsMenuLayoutEvent(SHOTS_LAYOUT_LARGE))
            dismiss()
        }
        view.homePopupSmallLayout.setOnClickListener {
            RxBus.get().post(ShotsMenuLayoutEvent(SHOTS_LAYOUT_SMALL))
        }
        view.homePopupOnlyImageLayout.setOnClickListener {
            RxBus.get().post(ShotsMenuLayoutEvent(SHOTS_LAYOUT_ONLY_IMAGE))
        }
    }
}