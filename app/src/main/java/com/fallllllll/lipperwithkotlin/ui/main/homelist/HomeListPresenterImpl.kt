package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_SORT
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TIME
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TYPE
import com.fallllllll.lipperwithkotlin.core.constants.PAGE_COUNT
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BaseListPresenter
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsListFilterEvent
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListContract
import com.fallllllll.lipperwithkotlin.utils.changeLikeStatus
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by fall on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class HomeListPresenterImpl(val model: DribbbleModel, private val shotsListView: ShotsListContract.ShotsListView) : BaseListPresenter(), ShotsListContract.ShotsListPresenter {


    private var time: String by DelegatesExt.valuePreference(KEY_FILTER_TIME, "")
    private var sort: String by DelegatesExt.valuePreference(KEY_FILTER_SORT, "")
    private var type: String by DelegatesExt.valuePreference(KEY_FILTER_TYPE, "")


    private var refreshDisposable: Disposable? = null
    private var loadNextDisposable: Disposable? = null
    private var canShowError = true


    override fun onPresenterCreate() {
        super.onPresenterCreate()
        initRxBus()
    }

    override fun refreshData() {
        disposeRefresh()
        refreshDisposable = model.getShot(type, time, sort, "1")
                .map { it.changeLikeStatus() }
                .commonChange()
                .subscribeBy({
                    refreshFinish(it)
                    canShowError = false
                }, { if (canShowError) onRefreshError() else onReloadError() })
    }

    override fun loadNextPageData(page: Int) {
        disposeLoadNext()
        loadNextDisposable = model.getShot(type, time, sort, page.toString())
                .map { it.changeLikeStatus() }
                .commonChange()
                .subscribeBy({
                    if (it.size < PAGE_COUNT) {
                        loadLastPageDataFinish(it)
                    } else {
                        loadNextPageFinish(it)
                    }
                }, { onLoadNextError() })
    }

    override fun detach() {
        super.detach()
        disposeLoadNext()
        disposeRefresh()
    }

    private fun disposeLoadNext() {
        if (loadNextDisposable?.isDisposed == false) {
            loadNextDisposable?.dispose()
        }
    }

    private fun disposeRefresh() {
        if (refreshDisposable?.isDisposed == false) {
            refreshDisposable?.dispose()
        }
    }

    private fun initRxBus() {
        subscribeListFilterEvent()
    }

    private fun subscribeListFilterEvent() {
        compositeDisposable.add(RxBus.get().toFlowable<ShotsListFilterEvent>()
                .subscribeBy({
                    stopLoading()
                    checkAndRefreshData()
                }, { subscribeListFilterEvent() }))
    }

}