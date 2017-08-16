package com.fallllllll.lipperwithkotlin.ui.view.widget.glideimageview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by qqq34 on 2017/8/11.
 */
class GlideImageView(context: Context, attributeSet: AttributeSet) : ImageView(context, attributeSet) {
    private val setting =
            GlideImageViewSetting(context, attributeSet)

    var ratio = setting.aspectRatio
        set(value) {
            field = value
            invalidate()
        }

    var isCircle = setting.isCircle
        set(value) {
            field = value
            invalidate()
        }

    var placeHolder = -1
        set(value) {
            field = value
            invalidate()
        }
    var fallBack = -1
        set(value) {
            field = value
            invalidate()
        }
    var radius = 0
        set(value) {
            field = value
            invalidate()
        }

    init {
        isDrawingCacheEnabled = true
        setWillNotDraw(false)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var hms = heightMeasureSpec
        var wms = widthMeasureSpec

        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0f) {
            heightSize = (widthSize / ratio + 0.5f).toInt()
            hms = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
        } else if (widthMode != MeasureSpec.EXACTLY && (heightMode == MeasureSpec.EXACTLY) and (ratio != 0f)) {
            widthSize = (heightSize * ratio + 0.5f).toInt()
            wms = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY)
        }

        super.onMeasure(wms, hms)
    }

    override fun onDraw(canvas: Canvas) {
        if (drawable == null) {
            return
        }
        if (width == 0 && height == 0) {
            return
        }
        drawRadius(canvas, getBitmapFromDrawable(drawable))
    }

    private fun drawRadius(canvas: Canvas, bitmap: Bitmap?) {
        if (bitmap != null) {
            val paint = Paint()
            paint.color = 0xffffff
            paint.isAntiAlias = true
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
            val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
            canvas.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            val scaleWidth = width.toFloat() / bitmap.width
            val scaleHeight = height.toFloat() / bitmap.height
            val matrix = Matrix()
            matrix.postScale(scaleWidth, scaleHeight)
            val newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            canvas.drawBitmap(newBitmap, 0f, 0f, paint)
            canvas.restore()
        }

    }

    private fun getBitmapFromDrawable(drawable: Drawable): Bitmap? {
        try {
            var bitmap: Bitmap =
                    when (drawable) {
                        is BitmapDrawable -> getBitmapFromDrawable@ return drawable.bitmap
                        is ColorDrawable -> Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                        else -> Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                    }
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            return null
        }
    }
}