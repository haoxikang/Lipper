package com.fallllllll.lipperwithkotlin.ui.shot

import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.ui.login.LoginContract

/**
 * Created by 康颢曦 on 2017/10/24.
 */
class ShotPresenterImpl(private val dribbbleModel: DribbbleModel, private val shotActivityView: ShotActivityContract.ShotActivityView)
    : BasePresenter(), ShotActivityContract.ShotActivityPresenter {
    private val shot: ShotBean?=shotActivityView.getSerializationArgument(SHOT_BEAN_KEY) as ShotBean
    override fun onPresenterCreate() {
        if (shot!=null){
            shotActivityView.displayAnimationImage(shot.getHDImage())
            if (shot.bucketsCount!=null){            //判断shot内容是否完整，从搜索页面过来的shot信息是不完整的需要重新拉取
                shotActivityView.showUI(shot)
            }
        }
    }

}