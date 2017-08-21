package com.fallllllll.lipperwithkotlin.ui.search

import android.support.design.widget.AppBarLayout
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.widget.SearchView
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.expandFunction.getStatusBarHeight
import com.fallllllll.lipperwithkotlin.core.expandFunction.hideIme
import com.fallllllll.lipperwithkotlin.core.expandFunction.setImageTranslucent
import com.fallllllll.lipperwithkotlin.core.expandFunction.showIme
import com.fallllllll.lipperwithkotlin.ui.shoslist.SEARCH_TYPE
import com.fallllllll.lipperwithkotlin.ui.shoslist.ShotsListFragment
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by fall on 2017/6/27/027.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
class SearchActivity : BaseActivity() {

    private val animationDuration = 300L
    override fun initViewAndData() {
        setContentView(R.layout.activity_search)
        setImageTranslucent()
        initAppbar()
    }

    private fun initAppbar() {
        val layoutParams = searchToolbar.layoutParams as AppBarLayout.LayoutParams
        layoutParams.topMargin = getStatusBarHeight()
        searchToolbar.post {
            shotsAppbar.layoutParams.height = shotsAppbar.height + getStatusBarHeight()
        }
    }

    override fun initListeners() {
        backView.setOnClickListener {
            checkAndBack()
        }
        searchback.setOnClickListener {
            checkAndBack()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (fragment_container.visibility == View.INVISIBLE) {
                    showContainerAnimation(fragment_container)
                }
                searchView.hideIme()
                searchView.clearFocus()
                showFragment(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (fragment_container.visibility == View.VISIBLE) {
                    searchView.requestFocus()
                    hideContainerAnimation(fragment_container)
                }



                return true
            }

        })
    }

    override fun onEnterAnimationComplete() {
        searchView.requestFocus()
        searchView.showIme()
    }

    fun showContainerAnimation(view: View) {
        val animator = ViewAnimationUtils.createCircularReveal(view, view.width / 2, 0, 0f,
                Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat())
        animator.interpolator = AccelerateInterpolator()
        animator.duration = animationDuration
        view.visibility = View.VISIBLE
        animator.start()
    }

    fun hideContainerAnimation(view: View) {
        val animator = ViewAnimationUtils.createCircularReveal(view, view.width / 2, 0,
                Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat(), 0f)
        animator.interpolator = AccelerateInterpolator()
        animator.duration = animationDuration
        animator.start()
        doAsync {
            Thread.sleep(animationDuration)
            uiThread {
                view.visibility = View.INVISIBLE
            }
        }
    }

    private fun checkAndBack() {
        if (fragment_container.visibility == View.VISIBLE) {
            hideContainerAnimation(fragment_container)
        } else {
            searchback.background = null
            finishAfterTransition()
        }
    }

    override fun onBackPressed() {
        checkAndBack()
    }

    private fun showFragment(query: String?) {
        if (!query.isNullOrEmpty()) {
            val fm = supportFragmentManager
            val transaction = fm.beginTransaction()
            val fragment = ShotsListFragment.newInstance(SEARCH_TYPE, query!!)
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }

    }
}