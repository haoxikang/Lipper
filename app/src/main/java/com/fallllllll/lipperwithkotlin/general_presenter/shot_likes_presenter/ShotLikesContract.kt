package com.fallllllll.lipperwithkotlin.general_presenter.shot_likes_presenter

import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean

/**
 * Created by qqq34 on 2017/9/27.
 */
class ShotLikesContract {
    interface ShotLikesView{
        fun getShotLikesSuccess(shots:List<ShotBean>)
        fun getShotLikesFail()
    }
    interface ShotLikesPresenter:Contract.Presenter{
        fun getShotLikes()
    }
}