package com.fallllllll.lipperwithkotlin.ui.search

import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.SORT_POPULAR
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BaseListPresenter
import com.fallllllll.lipperwithkotlin.data.network.model.SearchModel
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListContract
import com.fallllllll.lipperwithkotlin.utils.changeLikeStatus
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by fall on 2017/7/6/006.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class SearchListPresenterImpl(val model: SearchModel, private val shotsListView: ShotsListContract.ShotsListView, private val kewWord: String) : BaseListPresenter(), ShotsListContract.ShotsListPresenter {
    private val per_page = 10
    private var refreshDisposable: Disposable? = null
    private var loadNextDisposable: Disposable? = null
    private var canShowError = true
    override fun refreshData() {
        disposeRefresh()
        shotsListView.setErrorViewVisible(false)
        refreshDisposable = model.search(kewWord, SORT_POPULAR, "1")
                .map { it.changeLikeStatus() }
                .commonChange()
                .subscribeBy({
                    if (it.isEmpty()) {
                        noDataLoad(R.string.search_no_data)
                    } else {
                        refreshFinish(it)
                        canShowError = false
                    }

                }, {
                    if (canShowError) onRefreshError() else onReloadError()
                })
    }

    override fun loadNextPageData(page: Int) {
        disposeLoadNext()
        loadNextDisposable = model.search(kewWord, SORT_POPULAR, page.toString())
                .map { it.changeLikeStatus() }
                .commonChange()
                .subscribeBy({
                    if (it.size < per_page) {
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