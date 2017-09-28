package com.fallllllll.lipperwithkotlin.general_presenter.LikeAndUnlikePresenter

import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by qqq34 on 2017/9/26.
 */
class LikeAndUnlikePresenterImpl(private val dribbbleModel: DribbbleModel, private val view: LikeAndUnlikeContract.LikeAndUnlikeView) :
        BasePresenter(), LikeAndUnlikeContract.LikeAndUnlikePresenter {


    override fun likeShot(shotBean: ShotBean, position: Int) {
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
                .subscribeBy({}, {
                    doLike(shotBean, position)
                }))
    }

    private fun like(shotBean: ShotBean, position: Int) {
        compositeDisposable.add(dribbbleModel.likeAShot(shotBean.id.toString())
                .commonChange()
                .subscribeBy(
                        {}, {
                    doUnLike(shotBean, position)
                }
                ))
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