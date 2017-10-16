package com.fallllllll.lipperwithkotlin.general_presenter.shot_likes_presenter

import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.LoginEvent
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.utils.LogUtils
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by qqq34 on 2017/9/27.
 */
class ShotLikesPresenterImpl(private val dribbbleModel: DribbbleModel, private val view: ShotLikesContract.ShotLikesView) :
        BasePresenter(), ShotLikesContract.ShotLikesPresenter {
    override fun getShotLikes() {
        compositeDisposable.add(dribbbleModel.getUserLikes(UserManager.instance.lipperUser?.id.toString())
                .commonChange()
                .subscribeBy({
                    if (it.isNotEmpty()) {
                        UserManager.instance.updateUserLike(it)
                        view.getShotLikesSuccess(it)
                    } else {
                        view.getShotLikesFail()
                    }
                }, {
                    view.getShotLikesFail()

                }))
    }

    override fun onPresenterCreate() {
        registerLoginEvent()
        if (UserManager.instance.isLogin()) {
            getShotLikes()
        }
    }

    private fun registerLoginEvent() {
        compositeDisposable.add(RxBus.get().toFlowable<LoginEvent>()
                .subscribeBy(
                        {
                            if (it.isLogin) {
                                getShotLikes()
                            } else {
                                view.cleanLikes()
                            }
                        }, { registerLoginEvent() }
                ))
    }

}