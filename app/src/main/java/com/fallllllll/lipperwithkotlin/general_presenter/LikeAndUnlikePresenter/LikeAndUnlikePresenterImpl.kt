package com.fallllllll.lipperwithkotlin.general_presenter.LikeAndUnlikePresenter

import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.databean.UserLikesBean
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by qqq34 on 2017/9/26.
 */
class LikeAndUnlikePresenterImpl(private val dribbbleModel: DribbbleModel, private val view: LikeAndUnlikeContract.LikeAndUnlikeView) :
        BasePresenter(), LikeAndUnlikeContract.LikeAndUnlikePresenter {


    override fun likeShot(shotBean: ShotBean, position: Int) {
        if (!UserManager.instance.isLogin()){
            view.showLikeLoginAnimation()
            return
        }
        if (shotBean.isLike) {
            doUnLike(shotBean, position)
            unLike(shotBean, position)
        } else {
            doLike(shotBean, position)
            like(shotBean, position)

        }
    }


    override fun onPresenterCreate() {
    }

    private fun unLike(shotBean: ShotBean, position: Int) {
        compositeDisposable.add(dribbbleModel.unlikeAShot(shotBean.id.toString())
                .commonChange()
                .subscribeBy({
                    if (UserManager.instance.isLogin()){
                        deleteFromUserLikeList(shotBean.id.toString())
                    }
                }, {
                    doLike(shotBean, position)
                }))
    }

    private fun like(shotBean: ShotBean, position: Int) {
        compositeDisposable.add(dribbbleModel.likeAShot(shotBean.id.toString())
                .commonChange()
                .subscribeBy(
                        {
                            if (UserManager.instance.isLogin()) {
                                it.shot = shotBean
                                addToUserLikeList(it)
                            } else {
                                doUnLike(shotBean, position)
                            }
                        }, {
                    doUnLike(shotBean, position)
                }))
    }

    private fun addToUserLikeList(userLikesBean: UserLikesBean) {
        UserManager.instance.addUserLike(userLikesBean)
    }

    private fun deleteFromUserLikeList(id: String) {
        UserManager.instance.removeUserLike(id)
    }

    private fun doLike(shotBean: ShotBean, position: Int) {
        shotBean.isLike = true
        shotBean.likesCount++
        view.like(position)


    }

    private fun doUnLike(shotBean: ShotBean, position: Int) {
        shotBean.isLike = false
        if (shotBean.likesCount > 0) {
            shotBean.likesCount--
        }
        view.like(position)
    }
}