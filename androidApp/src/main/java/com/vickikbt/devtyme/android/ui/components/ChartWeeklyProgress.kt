package com.vickikbt.devtyme.android.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.devtyme.android.utils.toChartData
import io.github.aakira.napier.Napier
import kotlin.math.roundToInt

@Composable
fun ChartWeeklyProgress(
    hoursWorked: List<Float>,
    daysOfWeek: List<String>?,
    modifier: Modifier = Modifier,
    graphColor: Color = MaterialTheme.colors.primary,
    transparentGraphColor: Color = graphColor.copy(alpha = .5f),
    textColor: Color = MaterialTheme.colors.onPrimary
) {
    Napier.e("Hours worked: $hoursWorked")

    val spacing = 100f
    val upperValue = remember { (hoursWorked.maxOfOrNull { it })?.roundToInt() ?: 24 }
    val lowerValue = 0
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
    Canvas(modifier = modifier) {

        // X-Axis
        val spacePerDay = (size.width - spacing) / 7
        (0..6).forEach { i ->
            val dayOfWeek = daysOfWeek?.get(i)?.toChartData()
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    dayOfWeek ?: "",
                    spacing + i * spacePerDay,
                    size.height - 6,
                    textPaint
                )
            }
        }

        // Y-Axis
        val spacePerHour = (size.width - spacing) / hoursWorked.size
        val hoursStep = (upperValue - lowerValue) / 4
        (0..upperValue).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    (lowerValue + hoursStep * i).toString(),
                    30f,
                    size.height - spacing - i * size.height / 4,
                    textPaint
                )
            }
        }

        // Graph
        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            for (i in hoursWorked.indices) {
                val hourWorked = hoursWorked[i]
                val nextInfo = hoursWorked.getOrNull(i + 1) ?: hoursWorked.last()
                val leftRatio = (hourWorked - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerHour
                val y1 = height - spacing - (leftRatio * height)
                val x2 = spacing + (i + 1) * spacePerHour
                val y2 = height - spacing - (rightRatio * height)
                if (i == 0) moveTo(x1, y1)
                lastX = (x1 + x2) / 2f
                quadraticBezierTo(x1, y1, lastX, (y1 + y2) / 2f)
            }
        }
        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )
        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}
