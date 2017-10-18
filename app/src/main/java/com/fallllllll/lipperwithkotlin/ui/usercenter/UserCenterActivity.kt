package com.fallllllll.lipperwithkotlin.ui.usercenter

import android.view.View
import android.widget.FrameLayout
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.getNavigationBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.getStatusBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import kotlinx.android.synthetic.main.activity_user_center.*

/**
 * Created by fall on 2017/7/18/018.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class UserCenterActivity : BaseActivity() {
    override fun initListeners() {
        userCenterToolbar.setNavigationOnClickListener { finishAfterTransition() }
        uploadButton.setOnClickListener { showToast("点击了上传") }
    }

    override fun initViewAndData() {
        setContentView(R.layout.activity_user_center)
        setImageTranslucent()
        initToolbar()
        initAppbarBg()
        initFloatActionButton()
        showUI(UserManager.instance.lipperUser)
        showFragment()

    }


    private fun showUI(lipperUser: LipperUser?) {
        if (lipperUser != null) {
            userCenterImage.loadImage(url = lipperUser.avatarUrl ?: "")
            userName.text = lipperUser.username
            userLocation.text = lipperUser.location
            userBio.text = lipperUser.bio?.replace("\n", " ")
            textFollowerCount.text = lipperUser.followersCount.toString()
            textFollowingCount.text = lipperUser.followingsCount.toString()
            if (lipperUser.type == "Player") {
                uploadButton.visibility = View.VISIBLE
            } else {
                uploadButton.visibility = View.GONE

            }
        }


    }

    private fun initFloatActionButton() {
        val layoutParams = uploadButton.layoutParams as FrameLayout.LayoutParams
        layoutParams.bottomMargin = getNavigationBarHeight()
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

    private fun showFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.userCenterShotsListContainer, UserCenterShotsListFragment())
        fragmentTransaction.commit()

    }
}