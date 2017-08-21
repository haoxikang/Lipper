package com.fallllllll.lipperwithkotlin.ui.main.home

import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_SORT
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TIME
import com.fallllllll.lipperwithkotlin.core.constants.KEY_FILTER_TYPE
import com.fallllllll.lipperwithkotlin.core.expandFunction.isTokenOutOfDate
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.core.rxjava.RxBus
import com.fallllllll.lipperwithkotlin.data.databean.eventBean.LoginEvent
import com.fallllllll.lipperwithkotlin.data.local.datatank.DelegatesExt
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by fall on 2017/6/18.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class ShotsActivityPresenterImpl(private val dribbbleModel: DribbbleModel, private val view: ShotsActivityContract.ShotsActivityView)
    : BasePresenter(), ShotsActivityContract.ShotsActivityPresenter {
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

    private var time: String by DelegatesExt.valuePreference(KEY_FILTER_TIME, "")
    private var sort: String by DelegatesExt.valuePreference(KEY_FILTER_SORT, "")
    private var type: String by DelegatesExt.valuePreference(KEY_FILTER_TYPE, "")


    override fun onPresenterCreate() {
        subscribeLoginEvent()
        if (UserManager.get().isLogin()) {
            view.showUserUI(UserManager.get().lipperUser)
            updateUserData()
        } else {
            view.showUserImagePlaceHolder()
        }
    }

    private fun updateUserData() {
        val userManager = UserManager.get()
        dribbbleModel.getUserInfo(userManager.access_token)
                .commonChange()
                .subscribeBy({
                    userManager.updateUser(it)
                    view.showUserUI(userManager.lipperUser)
                }, {
                    onError(it)
                })
    }

    private fun onError(throwable: Throwable) {
        if (throwable.isTokenOutOfDate()) {
            view.showErrorDialog(view.getString(R.string.login_expire))
            UserManager.get().logOut()
        }
    }

    private fun subscribeLoginEvent() {
        compositeDisposable.add(RxBus.get().toFlowable<LoginEvent>()
                .subscribeBy({
                    if (it.isLogin) {
                        view.showUserUI(UserManager.get().lipperUser)
                    } else {
                        view.showUserImagePlaceHolder()
                    }
                }, { subscribeLoginEvent() }))
    }
}