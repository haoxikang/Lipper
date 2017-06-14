package com.fallllllll.lipperwithkotlin.core.expandFunction

import com.fallllllll.lipperwithkotlin.core.rxjava.ConvertToAPIException
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by fallllllll on 2017/6/13/013.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun <T> io.reactivex.Flowable<T>.commonChange() : io.reactivex.Flowable<T> {
return    this.onErrorResumeNext(com.fallllllll.lipperwithkotlin.core.rxjava.ConvertToAPIException())
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
}
