package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_SORT
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TIME
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TYPE
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.databean.HomeListFilterBean
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt

/**
 * Created by 康颢曦 on 2017/6/18.
 */
class ShotsActivityPresenter(val view: ShotsActivityContract.ShotsActivityView) : BasePresenter(), ShotsActivityContract.ShotsActivityPresenter {

    var time: String by DelegatesExt.valuePreference(KEY_FILTER_TIME, "")
    var sort: String by DelegatesExt.valuePreference(KEY_FILTER_SORT, "")
    var type: String by DelegatesExt.valuePreference(KEY_FILTER_TYPE, "")

    override fun showBottomSheet() {
        view.showBottomSheet(HomeListFilterBean(time,type,sort))
    }

    override fun onPresenterCreate() {
    }

}