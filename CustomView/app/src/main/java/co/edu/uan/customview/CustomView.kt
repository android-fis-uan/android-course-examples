package co.edu.uan.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

class CustomView : View {

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
        setMeasuredDimension(300,300)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if(canvas==null) return

        val black = Paint()
        black.setARGB(255,0,0,0)
        black.style = Paint.Style.STROKE
        black.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        black.textSize = 30f


        val yellow = Paint()
        yellow.setARGB(255,255,255,0)
        yellow.style = Paint.Style.FILL_AND_STROKE

        val blue= Paint()
        blue.setARGB(255,0,0,255)
        blue.style = Paint.Style.FILL_AND_STROKE

        val red= Paint()
        red.setARGB(255,255,0,0)
        red.style = Paint.Style.FILL_AND_STROKE

        canvas.drawCircle(140f,100f,100f, black) // face
        canvas.drawCircle(140f,100f,100f, yellow) // face
        canvas.drawCircle(100f,80f,15f, blue) // eye
        canvas.drawCircle(190f,80f,15f, blue) // eye
        black.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(145f,110f,10f, black) // nose
        val rect = RectF(120f,150f,120f+50,150f+20)
        canvas.drawRect(rect, red) // mouth
        canvas.drawText("Android is awesome!",0f,250f, black) // text

    }

}
