package com.fallllllll.lipperwithkotlin.ui.login

import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import com.fallllllll.lipperwithkotlin.data.network.model.impl.DribbbleModelImpl
import com.fallllllll.lipperwithkotlin.data.network.model.impl.OauthModelImpl
import com.fallllllll.lipperwithkotlin.utils.RxSchedulersOverrideRule
import com.fallllllll.lipperwithkotlin.utils.initUser
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.annotation.Config

/**
 * Created by fallllllll on 2017/6/21/021.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class LoginPresenterImplTest {
    @Rule
    @JvmField
    val mRxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockLoginView: LoginContract.LoginView

    lateinit var dribbbleModel: DribbbleModel

    lateinit var oauthModel: OauthModel

    lateinit var loginPresenter: LoginContract.LoginPresenter

    @Before
    fun beforeTest() {
        initUser()
        dribbbleModel = DribbbleModelImpl.getInstance()
        oauthModel = OauthModelImpl.getInstance()
        loginPresenter = LoginPresenterImpl(dribbbleModel,oauthModel,mockLoginView)
        loginPresenter.attach()
    }

    @Test
    fun onPresenterCreate() {
        loginPresenter.onPresenterCreate()
        verify(mockLoginView).beforeLogin()
        verify(mockLoginView).showTopDialog(anyOrNull())
        verify(mockLoginView).hideAllTopDialog()
        verify(mockLoginView).loginSuccessful()
    }

    @After
    fun afterTest() {
        loginPresenter.detach()
    }
}