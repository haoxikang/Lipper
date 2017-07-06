package com.fallllllll.lipperwithkotlin.ui.shoslist

import com.fallllllll.lipperwithkotlin.core.presenter.Contract

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ShotsListContract {
     interface ShotsListView : Contract.BaseView {

        fun changeRecyclerViewLayout(layoutType: String)


        fun setErrorViewVisible(isShow: Boolean)

    }

     interface ShotsListPresenter : Contract.Presenter
}