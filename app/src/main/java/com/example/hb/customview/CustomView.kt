package com.example.hb.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.AnkoLogger
import kotlin.random.Random

class CustomView : View, AnkoLogger {

    companion object {
        const val DELTA = 8
        const val NB_CIRCLES = 5
    }

    // NOTE convention de nommage des variables : commence par mXXX
    private var mPaint = Paint()

    lateinit var mCircle: MagicCircle // init later with maxX and maxY

    // TODO Conventions de nommage : see le projet de reference : https://github.com/google/iosched/
    var circles: MutableList<MagicCircle> = ArrayList(NB_CIRCLES)
    val circlesColor = arrayOf(Color.BLACK, Color.CYAN, Color.GREEN, Color.RED, Color.YELLOW)


    /**
     * Constructors
     */
    constructor(context: Context) : this(context, null) // call next intern constructor

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
        initCircles()
    }


    /**
     * Functions
     */
    private fun init() {
        mPaint.color = Color.BLUE

        mCircle = MagicCircle(0F, 0F)
        mCircle.delta = DELTA
    }


    private fun initCircles() {

        for (i in 0..NB_CIRCLES) {

            // TODO init MagicCircles with random values
            val oneCircle = MagicCircle(0F, 0F)

            with(oneCircle) {
                delta = Random.nextInt(0, DELTA)
                color = circlesColor[Random.nextInt(0, (circlesColor.size)-1)] //circlesColor[(0..(circlesColor.size)-1).random()]
            }

            val leftLimit = 1F;
            val rightLimit = 500F;
            oneCircle.cx = leftLimit + Random.nextFloat() * (rightLimit - leftLimit)

            circles.add(i, oneCircle)
        }
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        /* NOT optimized to do new Object in layout functions
        mCircle = MagicCircle(width.toFloat(), height.toFloat())
        mCircle.delta = DELTA*/

        mCircle.maxX = width.toFloat()
        mCircle.maxY = height.toFloat()

        for (oneCircle in circles) {
            oneCircle.maxX = width.toFloat()
            oneCircle.maxY = height.toFloat()
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mCircle.move()
        canvas?.drawCircle(mCircle.cx, mCircle.cy, mCircle.rad, mPaint)

        for (oneCircle in circles) {
            oneCircle.move()
            mPaint.color = oneCircle.color
            canvas?.drawCircle(oneCircle.cx, oneCircle.cy, oneCircle.rad, mPaint)
        }

        invalidate() // refresh/invalidate the view => redraw the view
    }
}