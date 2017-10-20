package com.fallllllll.lipperwithkotlin.ui.transitions

import android.content.Context
import android.graphics.Rect
import android.transition.ChangeBounds
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.View

/**
 * Created by 康颢曦 on 2017/10/20.
 */
private const val PROP_NAME_BOUNDS = "android:changeBounds:bounds"
private const val PROP_NAME_PARENT = "android:changeBounds:parent"

class ShotSharedEnter(context: Context, attributeSet: AttributeSet) : ChangeBounds(context, attributeSet) {
    override fun captureEndValues(transitionValues: TransitionValues) {
        super.captureEndValues(transitionValues)
        val width = (transitionValues.values[PROP_NAME_PARENT] as View).width
        val bounds = transitionValues.values[PROP_NAME_BOUNDS] as Rect
        bounds.right = width
        bounds.bottom = width * 3 / 4
        transitionValues.values.put(PROP_NAME_BOUNDS, bounds)

    }

}