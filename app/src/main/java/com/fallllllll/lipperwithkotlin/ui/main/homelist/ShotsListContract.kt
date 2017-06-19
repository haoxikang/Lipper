package com.fallllllll.lipperwithkotlin.ui.main.homelist

import com.fallllllll.lipperwithkotlin.core.presenter.Contract

/**
 * Created by fallllllll on 2017/6/19/019.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ShotsListContract {
     interface ShotsListView : Contract.BaseView {

        fun changeRecyclerViewLayout(LayoutType: String)

        fun changeItemViewLayout(layoutType: String)

        fun setErrorViewVisible(isShow: Boolean)

    }

     interface ShotsListPresenter : Contract.Presenter
}