package com.fallllllll.lipperwithkotlin.ui.login

import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.constants.ACTIVITY_TRANSITIONS_TIME
import com.fallllllll.lipperwithkotlin.core.presenter.BasePresenter
import com.fallllllll.lipperwithkotlin.data.local.user.LipperUser
import com.fallllllll.lipperwithkotlin.data.local.user.UserManager
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import com.fallllllll.lipperwithkotlin.utils.LogUtils
import com.fallllllll.lipperwithkotlin.core.expandFunction.commonChange
import com.fallllllll.lipperwithkotlin.core.expandFunction.checkToken
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.TimeUnit

/**
 * Created by fallllllll on 2017/6/13/013.
 * GitHub :  https://github.com/348476129/Lipper
 */
class LoginPresenterImpl(val dribbbleModel: DribbbleModel, val oauthModel: OauthModel, val loginView: LoginContract.LoginView) : BasePresenter(), LoginContract.LoginPresenter {
    override fun getUserData(code: String) {
        loginView.setButtonEnable(false)
        loginView.showTopDialog(loginView.getString(R.string.under_login))
        val disposable = oauthModel.getToken(code)
                .flatMap {
                    LogUtils.d(it.access_token?:"")
                    UserManager.get().updateToken(it)
                    dribbbleModel.getUserInfo()
                }
                .delay(2,TimeUnit.SECONDS)
                .commonChange()
                .subscribeBy({ next(it) }, { error(it) })
        compositeDisposable.add(disposable)
    }

    override fun onPresenterCreate() {
        if (UserManager.get().isLogin()) {
            compositeDisposable.add(updateUserData())
        }
    }

    override fun onLoginClick() {
        if (UserManager.get().isLogin()) {
            updateUserData()
        } else {
            loginView.goWebActivityForResult()
        }
    }

    private fun updateUserData(): Disposable {
        loginView.setButtonEnable(false)
        loginView.showTopDialog(loginView.getString(R.string.under_login))
        val disposable = dribbbleModel.getUserInfo()
                .delay(2,TimeUnit.SECONDS)
                .commonChange()
                .subscribeBy({ next(it) }, { error(it) })
        return disposable
    }


    private fun finishActivity() {

                loginView.finishActivity()

    }


    private fun next(lipperUser: LipperUser) {
        UserManager.get().updateUser(lipperUser)
        loginView.hideAllTopDialog()
        loginView.goMainActivity()
        finishActivity()
    }

    private fun error(throwable: Throwable) {
        loginView.hideAllTopDialog()
        loginView.setButtonEnable(true)
        if (throwable.checkToken()) {
            loginView.showErrorDialog(loginView.getString(R.string.login_expire))
            UserManager.get().logOut()
        } else {
            loginView.showErrorDialog(loginView.getString(R.string.login_failed))
        }
    }
}