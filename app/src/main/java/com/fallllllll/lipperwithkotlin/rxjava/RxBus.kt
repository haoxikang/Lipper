package com.fallllllll.lipperwithkotlin.rxjava

import android.util.Log
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import java.util.*

/**
 * Created by fallllllll on 2017/5/31/031.
 * GitHub :  https://github.com/348476129/Lipper
 */

object RxBus {
    val bus :FlowableProcessor<Any>
    get() {
        Log.d("aaa","chushihua")
        val flowableProcessor: FlowableProcessor<Any> = PublishProcessor.create()
        return flowableProcessor
    }
}
