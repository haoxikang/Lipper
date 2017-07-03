package com.fallllllll.lipperwithkotlin.ui.login

import android.content.Intent
import android.net.Uri
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import com.fallllllll.lipperwithkotlin.ui.main.home.ShotsActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.uiThread
import javax.inject.Inject

/**
 * Created by fallllllll on 2017/6/14/014.
 * GitHub :  https://github.com/348476129/Lipper
 */


class LoginActivity : BaseActivity(), LoginContract.LoginView {
    override fun loginFinish() {
        loginButton.isEnabled = true
        rotateButton.isEnabled = true
    }

    @Inject lateinit var loginPresenter: LoginContract.LoginPresenter

    var loginModule: LoginModule? = null

    override fun goWebActivity() {
        startActivity<LoginWebActivity>()
    }


    override fun beforeLogin() {
        loginButton.isEnabled = false
        rotateButton.isEnabled = false
    }

    override fun loginSuccessful() {

        startExitAnimation()
        doAsync {
            Thread.sleep(400)
            uiThread {
                startActivity<ShotsActivity>()
                finish()
            }
        }

    }


    override fun initViewAndData() {
        DaggerLoginComponent.builder()
                .appComponent(AppApplication.instance.appComponent)
                .loginModule(loginModule ?: LoginModule(this))
                .build()
                .inject(this)
        presenterLifecycleHelper.addPresenter(loginPresenter)
        setContentView(R.layout.activity_login)
        setImageTranslucent()
        rotateButton.post {
            doAsync {
                Thread.sleep(200)
                uiThread {
                    startEnterAnimation()
                }
            }

        }
        presenterLifecycleHelper.onPresenterCreate()
    }

    override fun initListeners() {
        loginButton.setOnClickListener { loginPresenter.onLoginClick() }
        rotateButton.setOnClickListener { loginPresenter.goShotsActivity() }
    }


    private fun startEnterAnimation() {
        val transition = TransitionInflater.from(this@LoginActivity).inflateTransition(R.transition.login_button_enter)
        transition.interpolator = OvershootInterpolator(0.8f)
        TransitionManager.beginDelayedTransition(rlLayout, transition)
        rotateButton.visibility = View.VISIBLE
        loginButton.visibility = View.VISIBLE
    }

    private fun startExitAnimation() {
        val transition = TransitionInflater.from(this@LoginActivity).inflateTransition(R.transition.login_button_enter)
        transition.interpolator = FastOutSlowInInterpolator()
        TransitionManager.beginDelayedTransition(rlLayout, transition)
        rotateButton.visibility = View.GONE
        loginButton.visibility = View.GONE
    }
}

