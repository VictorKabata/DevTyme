package com.vickikbt.devtyme.android.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import java.text.SimpleDateFormat
import java.util.*

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Int.toHours(): String {
    return when {
        this == 0 -> ""
        this > 1 -> "${this}hrs"
        else -> "${this}hr"
    }
}

fun Int.toMinutes(): String {
    return if (this > 1) "${this}mins"
    else "${this}min"
}

fun Long.toHours(): String {
    return if (this > 1) "${this}hrs"
    else "${this}hr"
}

fun String.toPresentation(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEEE\ndd", Locale.getDefault())

    val date = inputFormat.parse(this)
    return outputFormat.format(date).toString()
}

fun String.toChartData(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEEE", Locale.getDefault())

    val date = inputFormat.parse(this)
    return outputFormat.format(date).toString()
}
