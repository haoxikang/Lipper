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
 * Created by fall on 2017/5/27/027.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
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

    override fun loadError(s: String?) {
        loadError(getString(R.string.retry_hint), R.drawable.ic_retry_black_48dp)
    }

    override fun loadError(res: Int) {
        loadError(getString(res))
    }
    override fun loadError(s: String?, res: Int) {
        loadErrorView()
        errorLayout.setOnClickListener {
            presenter.checkAndRefreshData()
            errorLayout.visibility = View.GONE
        }
        errorView.errorImage.setImageResource(res)
        errorView.errorText.text = s
    }

    override fun loadError() {
        loadError(getString(R.string.retry_hint))
    }

    override fun loadNextPageError() {
        loadNextPageError(getString(R.string.failed_to_load))
    }

    override fun loadNextPageError(s: String?) {
        loadNextPageError(s, -1)
    }

    override fun loadNextPageError(res: Int) {
        loadNextPageError(getString(res))
    }
    override fun loadNextPageError(s: String?, res: Int) {
        showSnackBar(s ?: getString(R.string.failed_to_load), swipeRefreshLayout)
    }

    override fun noDataLoad() {
        noDataLoad(getString(R.string.no_data))
    }

    override fun noDataLoad(s: String?) {
        noDataLoad((s), R.drawable.ic_no_data_black_48dp)
    }

    override fun noDataLoad(res: Int) {
        noDataLoad(getString(res))
    }

    override fun noDataLoad(s: String?, res: Int) {
        swipeRefreshLayout.isEnabled = false
        loadErrorView()
        errorLayout.setOnClickListener { }
        errorView.errorImage.setImageResource(res)
        errorView.errorText.text = s
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