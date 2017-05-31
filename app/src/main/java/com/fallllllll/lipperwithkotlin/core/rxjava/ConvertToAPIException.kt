package com.fallllllll.lipperwithkotlin.core.rxjava

import com.fallllllll.lipperwithkotlin.core.exception.NetWorkException
import io.reactivex.Flowable
import io.reactivex.functions.Function

/**
 * Created by fallllllll on 2017/5/31/031.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ConvertToAPIException<T> : Function<Throwable, Flowable<T>> {
    override fun apply(t: Throwable?): Flowable<T> = Flowable.error(NetWorkException.handleException(t ?: Throwable("unknown")))
}