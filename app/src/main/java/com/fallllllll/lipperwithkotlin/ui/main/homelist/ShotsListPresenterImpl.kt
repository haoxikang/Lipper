package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.core.constants.*
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BaseListPresenter
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsListFilterEvent
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.ShotsMenuLayoutEvent
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ShotsListPresenterImpl(val model: DribbbleModel, val shotsListView: ShotsListContract.ShotsListView) : BaseListPresenter(), ShotsListContract.ShotsListPresenter {

    var currentLayoutType: String by DelegatesExt.valuePreference(KEY_LAYOUT_TYPE, SHOTS_LAYOUT_LARGE)
    var time: String by DelegatesExt.valuePreference(KEY_FILTER_TIME, "")
    var sort: String by DelegatesExt.valuePreference(KEY_FILTER_SORT, "")
    var type: String by DelegatesExt.valuePreference(KEY_FILTER_TYPE, "")


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
                .subscribeBy({ this::loadNextPageFinish }, { onLoadNextError() })
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

    private fun initRxBus() {
        subscribeLayoutEvent()
        subscribeListFilterEvent()
    }

    private fun subscribeLayoutEvent() {
        compositeDisposable.add(RxBus.get().toFlowable(ShotsMenuLayoutEvent::class.java)
                .subscribeBy({
                    setListLayout(it.shotLayoutType)
                }, { subscribeLayoutEvent() }))
    }

    private fun subscribeListFilterEvent() {
        compositeDisposable.add(RxBus.get().toFlowable(ShotsListFilterEvent::class.java)
                .subscribeBy({
                    stopLoading()
                    time = it.time
                    sort = it.sort
                    type = it.type
                    checkAndRefreshData()

                }, { subscribeListFilterEvent() }))
    }

    private fun setListLayout(currentLayoutType: String) {
        if (this.currentLayoutType == currentLayoutType) {
            if ((this.currentLayoutType == SHOTS_LAYOUT_SMALL || this.currentLayoutType == SHOTS_LAYOUT_ONLY_IMAGE) && (currentLayoutType == SHOTS_LAYOUT_SMALL || currentLayoutType == SHOTS_LAYOUT_ONLY_IMAGE)) {
                shotsListView.changeItemViewLayout(currentLayoutType)
            } else {
                shotsListView.changeRecyclerViewLayout(currentLayoutType)
            }
            this.currentLayoutType = currentLayoutType
        }
    }

}