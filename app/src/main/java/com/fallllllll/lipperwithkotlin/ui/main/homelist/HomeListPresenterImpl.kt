package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.core.constants.*
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BaseListPresenter
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsListFilterEvent
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsMenuLayoutEvent
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListContract
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class HomeListPresenterImpl(val model: DribbbleModel, val shotsListView: ShotsListContract.ShotsListView) : BaseListPresenter(), ShotsListContract.ShotsListPresenter {


   private var time: String by DelegatesExt.valuePreference(KEY_FILTER_TIME, "")
  private  var sort: String by DelegatesExt.valuePreference(KEY_FILTER_SORT, "")
  private  var type: String by DelegatesExt.valuePreference(KEY_FILTER_TYPE, "")


    private var refreshDisposable: Disposable? = null
    private var loadNextDisposable: Disposable? = null
    private var canShowError = true


    override fun onPresenterCreate() {
        super.onPresenterCreate()
        initRxBus()
    }

    override fun refreshData() {
        disposeRefresh()
        shotsListView.setErrorViewVisible(false)
        refreshDisposable = model.getShot(type, time, sort, "1")
                .commonChange()
                .subscribeBy({
                    refreshFinish(it)
                    canShowError = false
                }, { if (canShowError) onRefreshError() else onReloadError() })
    }

    override fun loadNextPageData(page: Int) {
        disposeLoadNext()
        loadNextDisposable = model.getShot(type, time, sort, page.toString())
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
        if (!(loadNextDisposable?.isDisposed ?: true)) {
            loadNextDisposable?.dispose()
        }
    }

    private fun disposeRefresh() {
        if (!(refreshDisposable?.isDisposed ?: true)) {
            refreshDisposable?.dispose()
        }
    }

    fun initRxBus() {
        subscribeListFilterEvent()
    }

    private fun subscribeListFilterEvent() {
        compositeDisposable.add(RxBus.get().toFlowable<ShotsListFilterEvent>()
                .subscribeBy({
                    stopLoading()
                    if (time != it.time || sort != it.sort || type != it.type) {
                        time = it.time
                        sort = it.sort
                        type = it.type
                        checkAndRefreshData()
                    }
                }, { subscribeListFilterEvent() }))
    }


}