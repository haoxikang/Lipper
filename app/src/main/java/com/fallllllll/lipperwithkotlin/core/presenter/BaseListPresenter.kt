package com.fallllllll.lipperwithkotlin.core.presenter

import com.fall.generalrecyclerviewfragment.GeneralPresenter
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * Created by fall on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
abstract class BaseListPresenter : GeneralPresenter(), Contract.Presenter {
    protected lateinit var compositeDisposable: CompositeDisposable

    override fun attach() {
        compositeDisposable = CompositeDisposable()
    }

    override fun detach() {
        compositeDisposable.clear()

    }
}
