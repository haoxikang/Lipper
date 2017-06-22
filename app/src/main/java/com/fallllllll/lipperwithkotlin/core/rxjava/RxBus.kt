package com.fallllllll.lipperwithkotlin.core.rxjava

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor

/**
 * Created by fallllllll on 2017/5/31/031.
 * GitHub :  https://github.com/348476129/Lipper
 */

class RxBus private constructor() {
    val bus: FlowableProcessor<Any>

    init {
        bus = PublishProcessor.create()
    }

    fun post(any: Any) = bus.onNext(any)


    inline fun <reified T> toFlowable() = bus.ofType(T::class.java)


    companion object {
        fun get(): RxBus = Inner.rxBus

    }

    private object Inner {
        val rxBus = RxBus()

    }
}
