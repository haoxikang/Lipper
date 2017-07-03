package com.fallllllll.lipperwithkotlin.ui.login

import com.fallllllll.lipperwithkotlin.core.presenter.Contract

/**
 * Created by fallllllll on 2017/6/13/013.
 * GitHub :  https://github.com/348476129/Lipper
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