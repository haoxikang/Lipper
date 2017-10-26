package com.fallllllll.lipperwithkotlin.ui.shot

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean

/**
 * Created by 康颢曦 on 2017/10/24.
 */
class ShotActivityContract{
        interface ShotActivityView:Contract.BaseView{
            fun showUI(shotBean: ShotBean)
        }
    interface ShotActivityPresenter:Contract.Presenter
}