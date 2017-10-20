package com.fallllllll.lipperwithkotlin.ui.view.widget.glideimageview

import android.content.Context
import android.util.AttributeSet
import com.fallllllll.lipperwithkotlin.R

/**
 * Created by fall on 2017/8/16.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class GlideImageViewSetting(val context: Context, attributeSet: AttributeSet?) {
    var aspectRatio = 0f
    var isCircle = false
    var placeHolder = -1
    var fallBack = -1
    var radius = 0
    var canPlayGif=false

    init {
        if (attributeSet != null) {
            val styledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GlideImageView, 0, 0)
            aspectRatio = styledAttributes.getFloat(R.styleable.GlideImageView_aspectRatio, 0f)
            isCircle = styledAttributes.getBoolean(R.styleable.GlideImageView_isCircle, false)
            placeHolder = styledAttributes.getResourceId(R.styleable.GlideImageView_placeHolder, -1)
            fallBack = styledAttributes.getResourceId(R.styleable.GlideImageView_fallBack, placeHolder)
            radius = styledAttributes.getInt(R.styleable.GlideImageView_radius, 0)
            styledAttributes.recycle()
        }
    }
}