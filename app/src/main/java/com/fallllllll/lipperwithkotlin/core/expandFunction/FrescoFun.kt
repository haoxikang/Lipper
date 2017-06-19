package com.fallllllll.lipperwithkotlin.core.expandFunction

import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun SimpleDraweeView.showImage(width: Int, height: Int, url: String) {
    val request = com.facebook.imagepipeline.request.ImageRequestBuilder.newBuilderWithSource(android.net.Uri.parse(url))
            .setResizeOptions(com.facebook.imagepipeline.common.ResizeOptions(width, height))
            .build()
    val controller = com.facebook.drawee.backends.pipeline.Fresco.newDraweeControllerBuilder()
            .setOldController(controller)
            .setImageRequest(request)
            .build()
    setController(controller)
}

fun SimpleDraweeView.showImage(url: String, isAutoPlayAnimation: Boolean) {
    val controller = com.facebook.drawee.backends.pipeline.Fresco.newDraweeControllerBuilder()
            .setUri(url)
            .setAutoPlayAnimations(isAutoPlayAnimation)
            .build()
    setController(controller)
}

