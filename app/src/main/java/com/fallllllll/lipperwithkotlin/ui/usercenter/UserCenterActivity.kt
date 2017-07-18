package com.fallllllll.lipperwithkotlin.ui.usercenter

import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.getStatusBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import kotlinx.android.synthetic.main.activity_shots.*
import kotlinx.android.synthetic.main.activity_user_center.*

/**
 * Created by fallllllll on 2017/7/18/018.
 * GitHub :  https://github.com/348476129/Lipper
 */
class UserCenterActivity : BaseActivity() {
    override fun initListeners() {
        userCenterToolbar.setNavigationOnClickListener { finishAfterTransition() }
    }

    override fun initViewAndData() {
        setContentView(R.layout.activity_user_center)
        setImageTranslucent()
        initToolbar()
    }

    private fun initToolbar() {
        userCenterToolbar.title = ""
        val layoutParams = userCenterToolbar.layoutParams as FrameLayout.LayoutParams
        layoutParams.topMargin = getStatusBarHeight()
        setSupportActionBar(userCenterToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}