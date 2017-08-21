package com.fallllllll.lipperwithkotlin.ui.login

import com.fallllllll.lipperwithkotlin.core.presenter.Contract

/**
 * Created by fall on 2017/6/13/013.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
interface LoginContract {
    interface LoginView : Contract.BaseView {
        fun goWebActivity()
        fun beforeLogin()
        fun loginFinish()

        fun loginSuccessful()

    }

    interface LoginPresenter : Contract.Presenter {
        fun getUserData(code: String)
        fun onLoginClick()
        fun goShotsActivity()
    }
}