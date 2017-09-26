package com.fallllllll.lipperwithkotlin.general_presenter.LikeAndUnlikePresenter

import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel

/**
 * Created by qqq34 on 2017/9/26.
 */
class LikeAndUnlikePresenterImpl(private val dribbbleModel: DribbbleModel, private val view: LikeAndUnlikeContract.LikeAndUnlikeView) :
        BasePresenter(), LikeAndUnlikeContract.LikeAndUnlikePresenter {
    override fun likeShot(shotBean: ShotBean) {
    }


    override fun onPresenterCreate() {
    }

}