package com.fallllllll.lipperwithkotlin.ui.view.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.fallllllll.lipperwithkotlin.R
import java.util.*

/**
 * Created by fallllllll on 2017/7/17/017.
 * GitHub :  https://github.com/348476129/Lipper
 */
class BubbleView(context: Context, attributeSet: AttributeSet?, defStyle: Int) : View(context, attributeSet, defStyle) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    private var maxRadius = 0
    private var minRadius = 0
    private var bWidth = 200
    private var bHeight = 200
    private var bubbleColor = 0
    private var bubbleCount = 4
    private val bubbles = ArrayList<Bubble>()
    private val random by lazy {
        Random()
    }

    class Bubble(var radius: Int = 0, var cx: Float = 0f, var cy: Float = 0f, var vx: Float = 0f, var vy: Float = 0f, var paint: Paint?) {
        fun move() {
            cx += vx
            cy += vy

        }

        fun left() = (cx - radius).toInt()

        fun right() = (cx + radius).toInt()

        fun bottom() = (cy + radius).toInt()

        fun top() = (cy - radius).toInt()
    }

    init {
        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.BubbleView, defStyle, 0)
        (0..a.indexCount - 1)
                .map { a.getIndex(it) }
                .forEach {
                    when (it) {
                        R.styleable.BubbleView_bubbleCount -> bubbleCount = a.getInt(it, 4)
                        R.styleable.BubbleView_bubbleColor -> bubbleColor = a.getColor(it, ContextCompat.getColor(context, R.color.defaultBubbleColor))
                    }

                }
        a.recycle()
        (0..bubbleCount - 1)
                .forEach {
                    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
                    paint.color = bubbleColor
                    paint.style = Paint.Style.FILL
                    paint.strokeWidth = 0.toFloat()
                    val speedX = 1
                    val speedY = 1
                    bubbles.add(Bubble(paint = paint
                            , vx = if (random.nextBoolean()) speedX.toFloat() else -speedX.toFloat()
                            , vy = if (random.nextBoolean()) speedY.toFloat() else -speedY.toFloat()))
                }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        bWidth = resolveSize(bWidth, widthMeasureSpec)
        bHeight = resolveSize(bHeight, heightMeasureSpec)
        setMeasuredDimension(bWidth, bHeight)
        maxRadius = if (bWidth > bHeight) bHeight / 3 else bWidth / 3
        minRadius = if (bWidth > bHeight) bHeight / 5 else bWidth / 5
        bubbles.forEach {
            it.radius = random.nextInt(maxRadius - minRadius + 1) + minRadius
            it.cx = (random.nextInt(bWidth - it.radius) + it.radius).toFloat()
            it.cy = (random.nextInt(bHeight - it.radius) + it.radius).toFloat()


        }
    }

    override fun onDraw(canvas: Canvas) {
        val startTime = System.currentTimeMillis()
        bubbles.forEach {
            canvas.drawCircle(it.cx, it.cy, it.radius.toFloat(), it.paint)
            collisionDetectingAndChangeSpeed(it)
            it.move()
        }
        val stopTime = System.currentTimeMillis()
        val runTime = stopTime - startTime
        postInvalidateDelayed(Math.abs(runTime - 16))
    }

    fun collisionDetectingAndChangeSpeed(bubble: Bubble) {

        val left = left
        val top = top
        val right = right
        val bottom = bottom

        val speedX = bubble.vx
        val speedY = bubble.vy

        if (bubble.left() + bubble.radius <= left && speedX < 0) {
            bubble.vx = -bubble.vx
        } else if (bubble.top() + bubble.radius <= top && speedY < 0) {
            bubble.vy = -bubble.vy
        } else if (bubble.right() - bubble.radius >= right && speedX > 0) {
            bubble.vx = -bubble.vx
        } else if (bubble.bottom() - bubble.radius >= bottom && speedY > 0) {
            bubble.vy = -bubble.vy
        }
    }
}