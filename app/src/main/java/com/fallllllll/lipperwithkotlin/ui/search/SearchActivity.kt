package com.fallllllll.lipperwithkotlin.ui.search

import android.animation.Animator
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
import kotlinx.android.synthetic.main.activity_search.*


/**
 * Created by fallllllll on 2017/6/27/027.
 * GitHub :  https://github.com/348476129/Lipper
 */
class SearchActivity : BaseActivity() {


    override fun initViewAndData() {
        setContentView(R.layout.activity_search)
        setImageTranslucent()
        searchToolbar.setPadding(0, getStatusBarHeight(), 0, 0)
    }

    override fun initListeners() {
        backView.setOnClickListener {
            checkAndBack()
        }
        searchback.setOnClickListener {
            searchback.background = null
            finishAfterTransition()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                showContainerAnimation(fragment_container)
                searchView.hideIme()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
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
        animator.duration = 300
        view.visibility = View.VISIBLE
        animator.start()
    }

    fun hideContainerAnimation(view: View) {
        val animator = ViewAnimationUtils.createCircularReveal(view, view.width / 2, 0,
                Math.hypot(view.width.toDouble(), view.height.toDouble()).toFloat(), 0f)
        animator.interpolator = AccelerateInterpolator()
        animator.duration = 300
        animator.start()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
    }

    private fun checkAndBack() {
        if (fragment_container.visibility == View.VISIBLE) {
            hideContainerAnimation(fragment_container)
        } else {
            finishAfterTransition()
        }
    }

    override fun onBackPressed() {
        checkAndBack()
    }
}