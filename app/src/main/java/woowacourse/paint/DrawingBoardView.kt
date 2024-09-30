package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val path = Path()
    val paint = Paint()
    var brushThickness: Float = 5f

    init {
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.addOval(
                pointX,
                pointY,
                pointX + brushThickness,
                pointY + brushThickness,
                Path.Direction.CW
            )

            MotionEvent.ACTION_MOVE -> path.addOval(
                pointX,
                pointY,
                pointX + brushThickness,
                pointY + brushThickness,
                Path.Direction.CW
            )

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }
}

private const val TAG = "DrawingBoardView"