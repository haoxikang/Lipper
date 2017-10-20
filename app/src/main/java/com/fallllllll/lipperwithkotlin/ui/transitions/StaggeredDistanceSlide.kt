package com.fallllllll.lipperwithkotlin.ui.transitions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.TransitionValues
import android.transition.Visibility
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.fallllllll.lipperwithkotlin.R

/**
 * Created by 康颢曦 on 2017/10/20.
 */
private const val PROP_NAME_SCREEN_LOCATION = "android:visibility:screenLocation"

class StaggeredDistanceSlide(context: Context, attributeSet: AttributeSet) : Visibility(context, attributeSet) {
    var spread = 1

    init {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.StaggeredDistanceSlide)
        spread = a.getInteger(R.styleable.StaggeredDistanceSlide_spread, spread)
        a.recycle()
    }

    override fun onAppear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
        val position = endValues.values[PROP_NAME_SCREEN_LOCATION] as IntArray
        return createAnimator(view, sceneRoot.height.toFloat() + (position[1] * spread), 0f)
    }

    override fun onDisappear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
        val position = endValues.values[PROP_NAME_SCREEN_LOCATION] as IntArray
        return createAnimator(view, 0f, (sceneRoot.height + position[1] * spread).toFloat())

    }

    private fun createAnimator(view: View, startTranslationY: Float, endTranslationY: Float): Animator {
        view.translationY = startTranslationY
        val ancestralClipping: List<Boolean> = TransitionUtils.setAncestralClipping(view, false)
        val transitions = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, endTranslationY)
        transitions.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                TransitionUtils.restoreAncestralClipping(view, ancestralClipping)
            }
        })
        return transitions
    }
}
