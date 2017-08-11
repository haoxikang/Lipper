package com.fallllllll.lipperwithkotlin.ui.view.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.fallllllll.lipperwithkotlin.R

/**
 * Created by qqq34 on 2017/8/11.
 */
class ExpandingImageView(context: Context, attributeSet: AttributeSet) : ImageView(context, attributeSet) {
    private var ratio: Float

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ExpandingImageView)
        ratio = typedArray.getFloat(R.styleable.ExpandingImageView_aspectRatio,0.toFloat())
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var hms = heightMeasureSpec
        var wms = widthMeasureSpec

        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0.toFloat()) {
            heightSize = (widthSize /ratio + 0.5f).toInt()
            hms = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
        } else if (widthMode != MeasureSpec.EXACTLY && (heightMode == MeasureSpec.EXACTLY) and (ratio != 0.toFloat())) {
            widthSize = (heightSize * ratio + 0.5f).toInt()
            wms = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY)
        }

        super.onMeasure(wms, hms)
    }

}