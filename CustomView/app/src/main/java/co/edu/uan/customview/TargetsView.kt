package co.edu.uan.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class TargetsView : View {

    constructor(context: Context):super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet):super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //setMeasuredDimension(400,400)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if(canvas==null) return

        val white= Paint()
        white.setARGB(255,255,255,255)
        white.style = Paint.Style.FILL_AND_STROKE

        val red= Paint()
        red.setARGB(255,255,0,0)
        red.style = Paint.Style.FILL_AND_STROKE

        val width = canvas.width * 1.0f
        val height = canvas.height * 1.0f

        var radius = 200f
        var shiftx = 0f
        var shifty = 0f
        var color = red

        for(i in 1..5) {
            Log.e("TARGETS", "Draw target $shiftx , $shifty , $color")
            //canvas.drawCircle(200f, 200f, radius, color)
            canvas.drawOval(shiftx,shifty, width-shiftx, height-shifty,color)
            radius = radius - 40
            shiftx = shiftx + width/10.0f
            shifty = shifty + height/10.0f

            color = if(color == red) white else red
        }

    }

    // in MyView.kt
    fun myAnimationLoop() {
        val thread = Thread(Runnable {
            // code to execute in thread goes here
            myAnimationLoop()
        })
        thread.start()
    }

}
