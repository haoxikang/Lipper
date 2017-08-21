package com.fallllllll.lipperwithkotlin.ui.shoslist

import com.fallllllll.lipperwithkotlin.core.presenter.Contract

/**
 * Created by fall on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class ShotsListContract {
     interface ShotsListView : Contract.BaseView {

    //    fun changeRecyclerViewLayout(layoutType: String)


        fun setErrorViewVisible(isShow: Boolean)

    }

     interface ShotsListPresenter : Contract.Presenter
}