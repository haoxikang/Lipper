package com.fallllllll.lipperwithkotlin.ui.view.widget.arclayout

import android.content.Context
import android.util.AttributeSet
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.expandFunction.dpTopx

/**
 * Created by fallllllll on 2017/7/18/018.
 * GitHub :  https://github.com/348476129/Lipper
 */
 const val CROP_INSIDE = 0
 const val CROP_OUTSIDE = 1

 const val POSITION_BOTTOM = 0
 const val POSITION_TOP = 1
 const val POSITION_LEFT = 2
 const val POSITION_RIGHT = 3

class ArcLayoutSettings(val context: Context, attributeSet: AttributeSet?) {
    val position: Int
    val arcHeight: Float
    val cropInside: Boolean
    var elevation = 0.toFloat()

    init {
        if (attributeSet != null) {
            val styledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ArcLayout, 0, 0)
            arcHeight = styledAttributes.getDimension(R.styleable.ArcLayout_arc_height, context.dpTopx(10))
            val cropDirection = styledAttributes.getInt(R.styleable.ArcLayout_arc_cropDirection, CROP_INSIDE)
            cropInside = (cropDirection == CROP_INSIDE)
            position = styledAttributes.getInt(R.styleable.ArcLayout_arc_position, POSITION_BOTTOM)
            styledAttributes.recycle()
        } else {
            position = POSITION_BOTTOM
            arcHeight = 10.toFloat()
            cropInside = true
        }

    }
}