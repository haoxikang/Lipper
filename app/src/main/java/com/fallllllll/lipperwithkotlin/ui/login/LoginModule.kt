package com.fallllllll.lipperwithkotlin.ui.login

import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import dagger.Module
import dagger.Provides

/**
 * Created by fallllllll on 2017/6/13/013.
 * GitHub :  https://github.com/348476129/Lipper
 */
@Module
 class LoginModule(val loginView: LoginContract.LoginView) {
    @Provides
    fun provideLoginPresenter(dribbbleModel: DribbbleModel, oauthModel: OauthModel):
            LoginContract.LoginPresenter = LoginPresenterImpl(dribbbleModel, oauthModel, loginView)
}