package com.fallllllll.lipperwithkotlin.ui.login

import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.OvershootInterpolator
import com.fallllllll.AppApplication
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import com.fallllllll.lipperwithkotlin.ui.main.home.ShotsActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import javax.inject.Inject

/**
 * Created by fallllllll on 2017/6/14/014.
 * GitHub :  https://github.com/348476129/Lipper
 */


const val LOGIN_REQUEST_CODE = 100
const val LOGIN_CODE_KEY = "LoginActivity.code.key"

class LoginActivity : BaseActivity(), LoginContract.LoginView {

    @Inject lateinit var loginPresenter: LoginContract.LoginPresenter

    var loginModule: LoginModule? = null

    override fun goWebActivityForResult() {
        startActivityForResult<LoginWebActivity>(LOGIN_REQUEST_CODE)
    }


    override fun setButtonEnable(isEnable: Boolean) {
        loginButton.isEnabled = isEnable
    }

    override fun goMainActivity() {

        startActivity<ShotsActivity>()

    }

    override fun finishActivity() {
        finish()
    }

    override fun initViewAndData() {
        DaggerLoginComponent.builder().appComponent(AppApplication.instance.appComponent).loginModule(loginModule ?: LoginModule(this)).build().inject(this)
        presenterLifecycleHelper.addPresenter(loginPresenter)
        setContentView(R.layout.activity_login)
        setImageTranslucent()
        rotateButton.post { startAnimation() }
        presenterLifecycleHelper.onPresenterCreate()
    }

    override fun initListeners() {
        loginButton.setOnClickListener { loginPresenter.onLoginClick() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOGIN_REQUEST_CODE) {
            when (resultCode) {
                RESULT_OK -> {
                    if (data != null) {
                        val b = data.extras
                        val url = b.getString(LOGIN_CODE_KEY)
                        loginPresenter.getUserData(Uri.parse(url).getQueryParameter("code"))
                    }

                }
            }
        }
    }

    private fun startAnimation() {
        val duration = 700f
        val maxValue = 1000f

        val distanceRotateButton = beforeRotateButtonAnimation()
        val distanceLoginButton = beforeLoginButtonAnimation()


        val valueAnimator = ValueAnimator.ofFloat(0f, maxValue)
        valueAnimator.interpolator = OvershootInterpolator(0.8f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            val YRotateButton = 0 - rotateButton.height + distanceRotateButton * value / maxValue

            val YLoginButton = loginButton.height + rlLayout.height - distanceLoginButton * value / maxValue


            rotateButton.y = YRotateButton
            loginButton.y = YLoginButton

            if (value == maxValue) {
                valueAnimator.removeAllUpdateListeners()
            }


        }

        valueAnimator.setDuration(duration.toLong()).start()
    }

    private fun beforeRotateButtonAnimation(): Float {
        val rotateH = rotateButton.height.toFloat()
        val rotateButtonY = rotateButton.y
        val rotateButtonYs = 0 - rotateH
        val distance = rotateButtonY - rotateButtonYs
        rotateButton.y = rotateButtonYs
        rotateButton.visibility = View.VISIBLE
        return distance


    }

    private fun beforeLoginButtonAnimation(): Float {
        val layoutH = rlLayout.height.toFloat()
        val buttonH = loginButton.height.toFloat()
        val buttonY = loginButton.y
        val buttonYs = layoutH + buttonH
        val distance = buttonYs - buttonY
        loginButton.y = buttonYs
        loginButton.visibility = View.VISIBLE
        return distance
    }

}