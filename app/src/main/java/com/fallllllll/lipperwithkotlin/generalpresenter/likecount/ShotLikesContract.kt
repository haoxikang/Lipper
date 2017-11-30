package com.fallllllll.lipperwithkotlin.generalpresenter.likecount

import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.data.databean.UserLikesBean

/**
 * Created by qqq34 on 2017/9/27.
 */
class ShotLikesContract {
    interface ShotLikesView{
        fun getShotLikesSuccess(shots:List<UserLikesBean>)
        fun getShotLikesFail()
        fun cleanLikes()
    }
    interface ShotLikesPresenter:Contract.Presenter{
        fun getShotLikes()
    }
}