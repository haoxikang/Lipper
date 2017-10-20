package com.fallllllll.lipperwithkotlin.ui.shot

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.util.Pair
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.setTranslucentStatusBarAndNavigationBar
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import kotlinx.android.synthetic.main.activity_shot.*
import org.jetbrains.anko.startActivity

/**
 * Created by 康颢曦 on 2017/10/19.
 */
private const val SHOT_BEAN_KEY = "shotActivity.shotbean.key"

class ShotActivity : BaseActivity() {

    companion object {
        fun startShotActivity(activity: Activity,view: View, shotBean: ShotBean) {
            activity.startActivity<ShotActivity>(SHOT_BEAN_KEY to shotBean)
            val intent = Intent()
            intent.setClass(activity,ShotActivity::class.java)
            intent.putExtra(SHOT_BEAN_KEY,shotBean)
            val options=ActivityOptions.makeSceneTransitionAnimation(activity,
                    Pair.create(view,activity.getString(R.string.transition_shot))
            ,Pair.create(view,activity.getString(R.string.transition_shot_background)))
            activity.startActivity(intent,options.toBundle())

        }
    }

    override fun initViewAndData() {
        setContentView(R.layout.activity_shot)
        setTranslucentStatusBarAndNavigationBar()

        val shot: ShotBean? = intent.getSerializableExtra(SHOT_BEAN_KEY) as ShotBean
        var url: String
        url = if (!shot?.images?.hidpi.isNullOrEmpty()) {
            shot?.images?.hidpi!!
        } else {
            shot?.images?.normal ?: ""
        }
        shotImage.loadImage(url = url)
        Glide.with(this).asBitmap().load(url).into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                Palette.from(resource).generate {
                    val swatch = it.dominantSwatch
                    if (swatch != null) {
                    //    shotBackground.setBackgroundColor(swatch.rgb)
                        shotTitle.setTextColor(swatch.titleTextColor)
                        shotBody.setTextColor(swatch.bodyTextColor)
                    }
                }
            }

        })
    }

    override fun initListeners() {

    }
}