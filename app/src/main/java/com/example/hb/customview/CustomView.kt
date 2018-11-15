package com.example.hb.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomView : View {

    companion object {
        const val DELTA = 8
    }

    // NOTE convention de nommage des variables : commence par mXXX
    private var mPaint = Paint()

    lateinit var mCircle: MagicCircle // init later with maxX and maxY


    /**
     * Constructors
     */
    constructor(context: Context) : this(context, null) // call next intern constructor

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }


    /**
     * Functions
     */
    private fun init() {
        mPaint.color = Color.BLUE

        /* TODO try this for optimization => change MagicCircle
        mCircle = MagicCircle(0F, 0F)
        mCircle.delta = DELTA
        */
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mCircle = MagicCircle(width.toFloat(), height.toFloat()) //TODO optimize
        mCircle.delta = DELTA

        /* TODO for optimization
        mCircle.mMaxX = width.toFloat()
        mCircle.mMaxY = height.toFloat()
        */

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mCircle.move()

        canvas?.drawCircle(mCircle.cx, mCircle.cy, mCircle.rad, mPaint)
        invalidate() // refresh/invalidate the view => redraw the view
    }
}