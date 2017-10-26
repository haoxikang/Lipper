package com.fallllllll.lipperwithkotlin.ui.shot

import android.app.Activity
import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.util.Log
import android.util.Pair
import android.view.View
import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.getStatusBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.setTranslucentStatusBarAndNavigationBar
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.utils.LogUtils
import kotlinx.android.synthetic.main.activity_shot.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.px2dip
import org.jetbrains.anko.uiThread
import javax.inject.Inject

/**
 * Created by 康颢曦 on 2017/10/19.
 */
private const val SHOT_BEAN_KEY = "shotActivity.shotbean.key"

class ShotActivity : BaseActivity(), ShotActivityContract.ShotActivityView {

    private var isExitAnim = true
    private val location = intArrayOf(0, 0)

    @Inject lateinit var presenter: ShotActivityContract.ShotActivityPresenter
    private val shot: ShotBean? by lazy {
        intent.getSerializableExtra(SHOT_BEAN_KEY) as ShotBean
    }

    companion object {
        fun startShotActivity(activity: Activity?, view: View, shotBean: ShotBean) {
            if (activity != null) {
                val intent = Intent()
                intent.setClass(activity, ShotActivity::class.java)
                intent.putExtra(SHOT_BEAN_KEY, shotBean)
                val options = ActivityOptions.makeSceneTransitionAnimation(activity
                        , Pair.create(view, activity.getString(R.string.transition_shot_background))
                        , Pair.create(view, activity.getString(R.string.transition_shot)))
                activity.startActivity(intent, options.toBundle())
            }


        }
    }

    override fun initViewAndData() {
        setContentView(R.layout.activity_shot)
        setTranslucentStatusBarAndNavigationBar()
        if (shot != null) {
            dribbbleShotImage.loadWithUrl(url = shot!!.getHDImage(), canPlayGif = true, onLoadFinish = { initColor(it) })
            shotImage.loadWithUrl(url = shot!!.getHDImage())
            initToolbar()
            initPresenter()
        } else {
            finish()  //应该不会发生这样的情况
        }

    }

    private fun initToolbar() {

        back.setPadding(0, getStatusBarHeight(), 0, 0)
        back.layoutParams.height = getStatusBarHeight() + resources.getDimensionPixelSize(R.dimen.shot_back_height)


    }

    private fun initPresenter() {
        DaggerShotComponent.builder()
                .appComponent(AppApplication.instance.appComponent)
                .shotModule(ShotModule(shot!!, this))
                .build()
                .inject(this)
    }

    override fun initListeners() {
        appBar.addOnOffsetChangedListener { _, _ -> dribbbleShotImage.getLocationInWindow(location) }
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onSharedElementEnd(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
                fab.visibility = View.VISIBLE
            }

            override fun onSharedElementStart(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
                if (isExitAnim) {
                    doAsync {
                        Thread.sleep(350)
                        uiThread {
                            shotImage.visibility = View.GONE
                            dribbbleShotImage.visibility=View.VISIBLE
                            isExitAnim = false
                        }
                    }
                }

            }

        })
    }


    override fun finishAfterTransition() {
        shotImage.translationY = location[1].toFloat()
        shotImage.visibility = View.VISIBLE
        dribbbleShotImage.visibility=View.INVISIBLE


        fab.visibility = View.GONE
        super.finishAfterTransition()
    }

    private fun initColor(bitmap: Bitmap?) {
        if (bitmap == null) return
        Palette.from(bitmap).generate {
            val swatch = it.dominantSwatch
            if (swatch != null) {
                shotBackground.setBackgroundColor(swatch.rgb)
                back.setColorFilter(swatch.titleTextColor)
            }
        }
    }
}