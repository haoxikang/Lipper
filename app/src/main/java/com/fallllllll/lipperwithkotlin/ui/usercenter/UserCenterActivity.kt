package com.fallllllll.lipperwithkotlin.ui.usercenter

import android.widget.FrameLayout
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.getStatusBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
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
        initAppbarBg()
        showUI(UserManager.get().lipperUser)

    }

    private fun showUI(lipperUser: LipperUser) {
        userCenterImage.setImageURI(lipperUser.avatarUrl)
        userName.text = lipperUser.username
        userLocation.text = lipperUser.location
        //userEmal.text = lipperUser.
    }

    private fun initAppbarBg() {
        appbarLayout.post {
            val h = appbarLayout.height
            val w = appbarLayout.width

            userCenterBg.layoutParams.height = h
            userCenterBg.layoutParams.width = w

            userCenterBubble.layoutParams.height = h
            userCenterBubble.layoutParams.width = w

        }
    }

    private fun initToolbar() {
        userCenterToolbar.title = ""
        val layoutParams = userCenterToolbar.layoutParams as FrameLayout.LayoutParams
        layoutParams.topMargin = getStatusBarHeight()
        setSupportActionBar(userCenterToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}