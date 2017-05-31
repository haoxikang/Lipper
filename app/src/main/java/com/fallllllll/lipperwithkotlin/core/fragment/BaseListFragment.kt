package com.fallllllll.lipperwithkotlin.core.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.view.LayoutInflater
import android.view.View
import com.fall.generalrecyclerviewfragment.GeneralRecyclerViewFragment
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.BaseViewUtils
import com.fallllllll.lipperwithkotlin.core.expandFunction.showSnackBar
import com.fallllllll.lipperwithkotlin.core.presenter.Contract
import com.fallllllll.lipperwithkotlin.core.presenter.PresenterLifecycleHelper
import kotlinx.android.synthetic.main.view_list_error.view.*
import org.jetbrains.anko.doAsync

/**
 * Created by fallllllll on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/Lipper
 */
abstract class BaseListFragment : GeneralRecyclerViewFragment(), Contract.BaseView {
    protected lateinit var errorView: View
    protected lateinit var presenterLifecycleHelper: PresenterLifecycleHelper
    protected lateinit var baseViewUtils: BaseViewUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenterLifecycleHelper = PresenterLifecycleHelper()
        baseViewUtils = BaseViewUtils(context)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.accent)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterLifecycleHelper.destroyPresenter()
    }

    override fun loadError() {
        loadErrorView()
        errorLayout.setOnClickListener {
            presenter.checkAndRefreshData()
            errorLayout.visibility = View.GONE
        }
        errorView.errorImage.setImageResource(R.drawable.ic_retry_black_48dp)
        errorView.errorText.setText(R.string.retry_hint)
    }

    override fun loadNextPageError() {
        showSnackBar(getString(R.string.failed_to_load), swipeRefreshLayout)
    }

    fun loadEmpty() {
        swipeRefreshLayout.isEnabled = false
        loadErrorView()
        errorView.errorImage.setImageResource(R.drawable.ic_no_data_black_48dp)
        errorView.errorText.setText(R.string.no_data)
    }

    private fun loadErrorView() {
        errorLayout.visibility = View.VISIBLE
        if (errorLayout.childCount == 0) {
            errorView = LayoutInflater.from(context).inflate(R.layout.view_list_error, null)
            errorLayout.addView(errorView)
        }
    }
    override fun showToast(s: String) {
        baseViewUtils.showToast(s)
    }

    override fun showTopDialog(s: String) {
        baseViewUtils.showTopDialog(s)
    }

    override fun hideAllTopDialog() {
        baseViewUtils.hideAllTopDialog()
    }

    override fun showErrorDialog(s: String) {
        baseViewUtils.showErrorDialog(s)
    }
}