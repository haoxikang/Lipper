package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_SORT
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TIME
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TYPE
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.HomeListFilterBean
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.LoginEvent
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by 康颢曦 on 2017/6/18.
 */
class ShotsActivityPresenter(val view: ShotsActivityContract.ShotsActivityView) : BasePresenter(), ShotsActivityContract.ShotsActivityPresenter {
    override fun userImageClick() {
        if (UserManager.get().isLogin()) {
            view.goUserCenterActivity()
        } else {

            view.showUserImageLoginAnimation()
        }
    }

    override fun menuActivityClick() {
        if (UserManager.get().isLogin()) {
            view.goUserActivity()
        } else {
            view.showMenuLoginAnimation()
        }
    }

    var time: String by DelegatesExt.valuePreference(KEY_FILTER_TIME, "")
    var sort: String by DelegatesExt.valuePreference(KEY_FILTER_SORT, "")
    var type: String by DelegatesExt.valuePreference(KEY_FILTER_TYPE, "")

    override fun showBottomSheet() {
        view.showBottomSheet(HomeListFilterBean(time, type, sort))
    }

    override fun onPresenterCreate() {
        subscribeLoginEvent()
        if (UserManager.get().isLogin()) {
            view.showUserUI(UserManager.get().lipperUser)
        }


    }

    private fun subscribeLoginEvent() {
        compositeDisposable.add(RxBus.get().toFlowable<LoginEvent>()
                .subscribeBy({
                    if (it.isLogin) {
                        view.showUserUI(UserManager.get().lipperUser)
                    } else {
                        view.LogOut()
                    }
                }, { subscribeLoginEvent() }))
    }
}