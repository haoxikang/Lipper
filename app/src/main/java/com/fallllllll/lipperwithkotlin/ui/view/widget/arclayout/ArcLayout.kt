package com.fallllllll.lipperwithkotlin.ui.view.widget.arclayout

import android.content.Context
import android.graphics.*
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import android.graphics.Paint.FILTER_BITMAP_FLAG
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.PaintFlagsDrawFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Canvas.ALL_SAVE_FLAG


/**
 * Created by fallllllll on 2017/7/18/018.
 * GitHub :  https://github.com/348476129/Lipper
 */
class ArcLayout(context: Context, attributeSet: AttributeSet?, defStyle: Int) : FrameLayout(context, attributeSet, defStyle) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    private val setting = ArcLayoutSettings(context, attributeSet)
    private var h = 0f
    private var w = 0f
    private val clipPath by lazy { createClipPath() }

    init {
        setting.elevation = ViewCompat.getElevation(this)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            calculateLayout()
        }
    }

    override fun dispatchDraw(canvas: Canvas) {

        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        val saveCount = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
        super.dispatchDraw(canvas)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        canvas.drawPath(clipPath, paint)
        canvas.restoreToCount(saveCount)
        paint.xfermode = null

    }

    private fun calculateLayout() {
        h = measuredHeight.toFloat()
        w = measuredWidth.toFloat()
        if (h > 0 && w > 0) {
            ViewCompat.setElevation(this, setting.elevation)
            if (!setting.cropInside) {
                ViewCompat.setElevation(this, setting.elevation)
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(vie: View?, outline: Outline?) {
                        outline?.setConvexPath(clipPath)
                    }
                }
            }
        }
    }

    private fun createClipPath(): Path {
        val path = Path()
        val arcHeight = setting.arcHeight
        when (setting.position) {
            POSITION_BOTTOM -> {
                if (setting.cropInside) {
                    path.moveTo(0f, 0f)
                    path.lineTo(0f, h)
                    path.quadTo(w / 2.toFloat(), h - 2 * arcHeight, w, h)
                    path.lineTo(w, 0f)
                    path.close()
                } else {
                    path.moveTo(0f, 0f)
                    path.lineTo(0f, h - arcHeight)
                    path.quadTo(w / 2.toFloat(), h + arcHeight, w, h - arcHeight)
                    path.lineTo(w, 0.toFloat())
                    path.close()
                }
            }
            POSITION_TOP -> {
                if (setting.cropInside) {
                    path.moveTo(0f, h)
                    path.lineTo(0f, 0f)
                    path.quadTo(w / 2, 2 * arcHeight, w, 0f)
                    path.lineTo(w, h)
                    path.close();
                } else {
                    path.moveTo(0f, arcHeight)
                    path.quadTo(w / 2, -arcHeight, w, arcHeight)
                    path.lineTo(w, h)
                    path.lineTo(0f, h)
                    path.close()
                }
            }
            POSITION_LEFT -> {
                if (setting.cropInside) {
                    path.moveTo(w, 0f)
                    path.lineTo(0f, 0f)
                    path.quadTo(arcHeight * 2, h / 2, 0f, h)
                    path.lineTo(w, h)
                    path.close()
                } else {
                    path.moveTo(w, 0f)
                    path.lineTo(arcHeight, 0f)
                    path.quadTo(-arcHeight, h / 2, arcHeight, h)
                    path.lineTo(w, h)
                    path.close()
                }
            }
            POSITION_RIGHT -> {
                if (setting.cropInside) {
                    path.moveTo(0f, 0f)
                    path.lineTo(w, 0f)
                    path.quadTo(w - arcHeight * 2, h / 2, w, h)
                    path.lineTo(0f, h)
                    path.close()
                } else {
                    path.moveTo(0f, 0f)
                    path.lineTo(w - arcHeight, 0f)
                    path.quadTo(w + arcHeight, h / 2, w - arcHeight, h)
                    path.lineTo(0f, h)
                    path.close()
                }
            }
        }
        return path
    }
}