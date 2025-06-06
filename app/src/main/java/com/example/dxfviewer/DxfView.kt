package com.example.dxfviewer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class DxfView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = 0xFF000000.toInt()
        strokeWidth = 2f
    }

    private var entities: List<LineEntity> = emptyList()

    fun bindViewModel(owner: LifecycleOwner, viewModel: DxfViewModel) {
        viewModel.entities.observe(owner, Observer { list ->
            entities = list ?: emptyList()
            invalidate()
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        entities.forEach { line ->
            canvas.drawLine(line.x1, line.y1, line.x2, line.y2, paint)
        }
    }
}
