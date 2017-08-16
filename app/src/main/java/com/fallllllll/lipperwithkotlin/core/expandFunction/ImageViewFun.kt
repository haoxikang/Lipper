package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

/**
 * Created by qqq34 on 2017/8/11.
 */
fun ImageView.showImage(x: Int = -1, y: Int = -1
                        , url: String
                        , isCircleCrop: Boolean = false
                        , @DrawableRes placeHolderId: Int = -1
                        , scaleTypes: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP) {

    scaleType = scaleType


    val requestOptions = RequestOptions()
    if (x != -1 && y != -1) {
        requestOptions.override(x, y)
    }
    if (isCircleCrop) {
        requestOptions.transform(CircleCrop())
    }
    if (placeHolderId != -1) {
        requestOptions.placeholder(placeHolderId)
    }
    Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(this)
}


