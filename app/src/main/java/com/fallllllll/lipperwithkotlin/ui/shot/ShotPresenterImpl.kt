package com.fallllllll.lipperwithkotlin.ui.shot

import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.ui.login.LoginContract

/**
 * Created by 康颢曦 on 2017/10/24.
 */
class ShotPresenterImpl(private var shotBean: ShotBean,private val dribbbleModel: DribbbleModel,private val shotActivityView: ShotActivityContract.ShotActivityView)
    :BasePresenter(),ShotActivityContract.ShotActivityPresenter{
    override fun onPresenterCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}