package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.databean.HomeListFilterBean

/**
 * Created by 康颢曦 on 2017/6/18.
 */
open class ShotsActivityContract {
     interface ShotsActivityView {

        fun showBottomSheet(homeListFilterBean: HomeListFilterBean)
    }

     interface ShotsActivityPresenter : Contract.Presenter {
        fun showBottomSheet()
    }
}