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
fun com.facebook.drawee.view.SimpleDraweeView.display(width: Int, height: Int, url: String) {
    val request = com.facebook.imagepipeline.request.ImageRequestBuilder.newBuilderWithSource(android.net.Uri.parse(url))
            .setResizeOptions(com.facebook.imagepipeline.common.ResizeOptions(width, height))
            .build()
    val controller = com.facebook.drawee.backends.pipeline.Fresco.newDraweeControllerBuilder()
            .setOldController(controller)
            .setImageRequest(request)
            .build()
    setController(controller)
}

fun com.facebook.drawee.view.SimpleDraweeView.display(url: String, isAutoPlayAnimation: Boolean) {
    val controller = com.facebook.drawee.backends.pipeline.Fresco.newDraweeControllerBuilder()
            .setUri(url)
            .setAutoPlayAnimations(isAutoPlayAnimation)
            .build()
    setController(controller)
}

