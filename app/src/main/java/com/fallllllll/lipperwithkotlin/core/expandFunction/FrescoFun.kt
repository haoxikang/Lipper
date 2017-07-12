package com.fallllllll.lipperwithkotlin.core.expandFunction

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun SimpleDraweeView.showImage(width: Int, height: Int, url: String) {
    val request = ImageRequestBuilder
            .newBuilderWithSource(Uri.parse(url))
            .setResizeOptions(ResizeOptions(width, height))
            .build()
    val controller = Fresco.newDraweeControllerBuilder()
            .setOldController(controller)
            .setImageRequest(request)
            .build()
    setController(controller)
}

fun SimpleDraweeView.showImage(url: String, isAutoPlayAnimation: Boolean) {
    val controller = Fresco.newDraweeControllerBuilder()
            .setUri(url)
            .setAutoPlayAnimations(isAutoPlayAnimation)
            .build()
    setController(controller)
}

