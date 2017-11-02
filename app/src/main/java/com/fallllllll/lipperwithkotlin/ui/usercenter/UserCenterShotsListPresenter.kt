package com.fallllllll.lipperwithkotlin.ui.usercenter

import com.fallllllll.lipperwithkotlin.core.constants.PAGE_COUNT
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BaseListPresenter
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.function.Consumer

/**
 * Created by qqq34 on 2017/10/17.
 */
class UserCenterShotsListPresenter(val model: DribbbleModel) : BaseListPresenter() {
    private var refreshDisposable: Disposable? = null
    private var loadNextDisposable: Disposable? = null
    private var canShowError = true
    override fun refreshData() {
        disposeRefresh()
        refreshDisposable = model.getUserShots("1")
                .commonChange()
                .subscribeBy({
                    refreshFinish(it)
                    canShowError = false
                }, {
                    if (canShowError) onRefreshError() else onRefreshError()
                })
    }

    override fun loadNextPageData(page: Int) {
        disposeLoadNext()
        loadNextDisposable = model.getUserShots(page.toString())
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
}