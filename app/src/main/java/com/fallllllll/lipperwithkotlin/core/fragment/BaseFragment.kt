package com.fallllllll.lipperwithkotlin.core.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fallllllll.lipperwithkotlin.core.BaseViewUtils
import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.core.presenter.PresenterLifecycleHelper

/**
 * Created by fallllllll on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/Lipper
 */
abstract class BaseFragment : Fragment(), Contract.BaseView {
    private var baseViewUtils: BaseViewUtils? = null
    protected var presenterLifecycleHelper: PresenterLifecycleHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenterLifecycleHelper = PresenterLifecycleHelper()
        baseViewUtils = BaseViewUtils(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayout(), container, false)
        initView(view)
        initListeners()
        return view
    }

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun initView(view: View)

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