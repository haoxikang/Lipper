package com.fallllllll.lipperwithkotlin.ui.login

import android.support.v4.content.ContextCompat
import android.view.View
import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.ui.transitions.FabTransform
import com.fallllllll.lipperwithkotlin.ui.transitions.MorphTransform
import kotlinx.android.synthetic.main.activity_dribbble_login.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by fallllllll on 2017/6/30/030.
 * GitHub :  https://github.com/348476129/Lipper
 */
class DribbbleLoginActivity : BaseActivity(), LoginContract.LoginView {

    @Inject lateinit var loginPresenter: LoginContract.LoginPresenter
    var loginModule: LoginModule? = null
    override fun loginFinish() {
        dribbbleLoginButton.visibility = View.VISIBLE
        dribbbleLoginProgressbar.visibility = View.GONE
    }

    override fun beforeLogin() {
        dribbbleLoginButton.visibility = View.GONE
        dribbbleLoginProgressbar.visibility = View.VISIBLE
    }

    override fun goWebActivity() {
        startActivity<LoginWebActivity>()
    }


    override fun loginSuccessful() {
        finishAfterTransition()
    }

    override fun showTopDialog(s: String) {

    }

    override fun initViewAndData() {
        setContentView(R.layout.activity_dribbble_login)
        if (!FabTransform.setup(this, container)) {
            MorphTransform.setup(this, container, ContextCompat.getColor(this, R.color.primary), 5)
        }
        DaggerDribbbleLoginComponent.builder()
                .appComponent(AppApplication.instance.appComponent)
                .loginModule(loginModule ?: LoginModule(this))
                .build()
                .inject(this)
        presenterLifecycleHelper.addPresenter(loginPresenter)
        presenterLifecycleHelper.onPresenterCreate()
    }

    override fun initListeners() {
        blankLayout.setOnClickListener { finishAfterTransition() }
        dribbbleLoginButton.setOnClickListener { loginPresenter.onLoginClick() }

    }


}