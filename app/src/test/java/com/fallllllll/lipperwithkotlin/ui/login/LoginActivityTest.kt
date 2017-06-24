package com.fallllllll.lipperwithkotlin.ui.login

import android.content.Intent
import com.fallllllll.lipperwithkotlin.BuildConfig
import com.fallllllll.lipperwithkotlin.core.MyRobolectricTestRunner
import com.fallllllll.lipperwithkotlin.data.network.model.DribbbleModel
import com.fallllllll.lipperwithkotlin.data.network.model.OauthModel
import com.fallllllll.lipperwithkotlin.ui.main.home.ShotsActivity
import com.fallllllll.lipperwithkotlin.utils.getActivityController
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

/**
 * Created by fallllllll on 2017/6/21/021.
 * GitHub :  https://github.com/348476129/Lipper
 */
@RunWith(MyRobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class LoginActivityTest {
    @Rule
    @JvmField
    val mMockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockLoginModule: LoginModule
    @Mock
  lateinit   var loginPresenter: LoginContract.LoginPresenter

   lateinit private var loginActivity: LoginActivity
  lateinit  private var controller: ActivityController<LoginActivity>

    @Before
    fun beforeTest(){
        controller = getActivityController<LoginActivity>()
        loginActivity = controller.get()

        `when`(mockLoginModule.provideLoginPresenter(any<DribbbleModel>(),
           any<OauthModel>())).thenReturn(loginPresenter)
        loginActivity.loginModule=mockLoginModule
        controller.create().start().resume()
    }

    @Test
    fun testVisitorLogin(){
        loginActivity.rotateButton.callOnClick()
        verify(loginPresenter).goShotsActivity()
    }

    @Test
    fun testLoginActivity(){
        verify(loginPresenter).attach()
        verify(loginPresenter).onPresenterCreate()

        val loginButton = loginActivity.loginButton
        loginButton.callOnClick()
        verify(loginPresenter).onLoginClick()

        controller.destroy()
        verify(loginPresenter).detach()
    }

    @Test
    fun goWebActivityForResult() {
        loginActivity.goWebActivityForResult()
        val shadowActivity = shadowOf(loginActivity)
        val actualIntent = shadowActivity.nextStartedActivityForResult
        assertEquals(actualIntent.requestCode,LOGIN_REQUEST_CODE)

    }

    @Test
    fun goMainActivity() {
        loginActivity.goMainActivity()
     val   intent = Intent(loginActivity,ShotsActivity::class.java)
        val shadowActivity = shadowOf(loginActivity)
        val actualIntent = shadowActivity.nextStartedActivity
        assertEquals(intent.toString(), actualIntent.toString())
    }

}