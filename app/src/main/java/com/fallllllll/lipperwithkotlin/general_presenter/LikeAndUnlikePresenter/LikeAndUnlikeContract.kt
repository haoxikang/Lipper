package com.fallllllll.lipperwithkotlin.general_presenter.LikeAndUnlikePresenter

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean

/**
 * Created by qqq34 on 2017/9/26.
 */
class LikeAndUnlikeContract{
    interface LikeAndUnlikeView{
        fun like(position:Int)
        fun showLikeLoginAnimation()
    }
    interface LikeAndUnlikePresenter: Contract.Presenter{
        fun likeShot(shotBean: ShotBean,position: Int)
    }
}