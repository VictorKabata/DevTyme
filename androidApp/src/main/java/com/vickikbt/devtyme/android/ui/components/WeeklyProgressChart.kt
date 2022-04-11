package com.vickikbt.devtyme.android.ui.components

import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun WeeklyProgressChart(
    totalHours: List<Float>,
    modifier: Modifier = Modifier,
    graphColor: Color = MaterialTheme.colors.primary,
    transparentGraphColor: Color = graphColor.copy(alpha = .5f),
    textColor: Color = MaterialTheme.colors.onPrimary
) {

    val spacing = 100f
    val transparentColor by remember { mutableStateOf(transparentGraphColor) }
    val upperValue by remember {
        mutableStateOf(totalHours.maxOfOrNull { it.coerceAtLeast(0f) }?.plus(1)?.roundToInt() ?: 0)
    }

    val lowerValue by remember {
        mutableStateOf(totalHours.minOfOrNull { it }?.toInt() ?: 0)
    }

    val density = LocalDensity.current
    val textPaint by remember {
        mutableStateOf(
            android.graphics.Paint().apply {
                color = android.graphics.Color.WHITE
                textAlign = android.graphics.Paint.Align.CENTER
                textSize = density.run { 12.sp.toPx() }
            }
        )
    }

    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / totalHours.size
        (0 until totalHours.size - 1 step 2).forEach { i ->
            val hourWorked = totalHours[i]

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hourWorked.toString(),
                    spacing + i * spacePerHour,
                    size.height - 5,
                    textPaint
                )
            }

            val daysOfWeekStep = (upperValue - lowerValue) / 5f
            (1..6).forEach { i ->
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        round(lowerValue + daysOfWeekStep * i).toString(),
                        30f,
                        size.height - spacing - i * size.height / 5f,
                        textPaint
                    )
                }
            }

            val strokePath = Path().apply {
                val height = size.height
                for (i in totalHours.indices) {
                    val hoursWorked = totalHours[i]
                    val nextHour = totalHours.getOrNull(i + 1) ?: totalHours.last()
                    val leftRatio = (hoursWorked - lowerValue) / (upperValue - lowerValue)
                    val rightRatio = (nextHour - lowerValue) / (upperValue - lowerValue)

                    val xAxis = spacing + i * spacePerHour
                    val yAxis = height - spacing - (leftRatio * height).toFloat()
                    if (i == 0) moveTo(xAxis, yAxis)
                }
            }
        }
    }
}
