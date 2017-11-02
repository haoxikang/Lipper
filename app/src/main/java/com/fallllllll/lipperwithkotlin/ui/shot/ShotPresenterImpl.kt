package com.fallllllll.lipperwithkotlin.ui.shot

import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.expandFunction.isTokenOutOfDate
import com.fallllllll.lipperwithkotlin.core.expandFunction.isTooManyRequest
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.databean.ShotBean
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by 康颢曦 on 2017/10/24.
 */
class ShotPresenterImpl(private val dribbbleModel: DribbbleModel, private val shotActivityView: ShotActivityContract.ShotActivityView)
    : BasePresenter(), ShotActivityContract.ShotActivityPresenter {
    private val shot: ShotBean? = shotActivityView.getSerializationArgument(SHOT_BEAN_KEY) as ShotBean
    override fun onPresenterCreate() {
        if (shot != null) {
            shotActivityView.displayAnimationImage(shot.getHDImage())
            if (shot.bucketsCount != null) {            //判断shot内容是否完整，从搜索页面过来的shot信息是不完整的需要重新拉取
                shotActivityView.showUI(shot)
            } else {
                shotActivityView.showLoadShotView()
                getShotById(shot)
            }
        }
    }

    private fun getShotById(shotBean: ShotBean) {
        compositeDisposable.add(dribbbleModel.getShotById(shotBean.id.toString())
                .commonChange()
                .subscribeBy(
                        shotActivityView::showUI, {
                    if (it.isTooManyRequest()) {
                        shotActivityView.getShotFail(shotActivityView.getString(R.string.too_many_request))
                        return@subscribeBy

                    }
                    if (it.isTokenOutOfDate()) {
                        shotActivityView.getShotFail(shotActivityView.getString(R.string.login_expire))
                        UserManager.instance.logOut()
                        return@subscribeBy
                    }
                    shotActivityView.getShotFail(shotActivityView.getString(R.string.failed_to_load))

                }))
    }
}