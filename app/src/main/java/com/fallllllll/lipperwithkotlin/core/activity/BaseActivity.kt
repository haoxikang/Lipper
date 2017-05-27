package com.fallllllll.lipperwithkotlin.core.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fallllllll.lipperwithkotlin.core.BaseViewUtils
import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.core.presenter.PresenterLifecycleHelper

/**
 * Created by fallllllll on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/Lipper
 */
abstract class BaseActivity : AppCompatActivity(), Contract.BaseView {
    private var baseViewUtils: BaseViewUtils? = null
    protected var presenterLifecycleHelper: PresenterLifecycleHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenterLifecycleHelper = PresenterLifecycleHelper()
        baseViewUtils = BaseViewUtils(this)
        initViewAndData()
        initListeners()
    }

    protected abstract fun initViewAndData()
    protected abstract fun initListeners()

    override fun onDestroy() {
        super.onDestroy()
        presenterLifecycleHelper?.destroyPresenter()
    }

    override fun showToast(s: String) {
        baseViewUtils?.showToast(s)
    }

    override fun showTopDialog(s: String) {
        baseViewUtils?.showTopDialog(s)
    }

    override fun hideAllTopDialog() {
        baseViewUtils?.hideAllTopDialog()
    }

    override fun showErrorDialog(s: String) {
        baseViewUtils?.showErrorDialog(s)
    }
}