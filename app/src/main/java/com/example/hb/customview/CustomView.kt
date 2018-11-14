package com.example.hb.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomView : View {

    companion object {
        val DELTA = 8
    }

    // NOTE convention de nommage des variables : commence par mXXX
    private var mPaint = Paint()

    lateinit var mCircle: MagicCircle // init later with maxX and maxY


    /**
     * Constructors
     */
    constructor(context: Context) : super(context, null)


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }


    /**
     * Functions
     */
    private fun init() {
        mPaint.color = Color.BLUE
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mCircle = MagicCircle(width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mCircle.delta = DELTA
        mCircle.move()

        canvas?.drawCircle(mCircle.cx, mCircle.cy, mCircle.rad, mPaint)
        invalidate() // refresh/invalide la vue => rappelle la vue et donc le onDraw
    }
}