package com.vickikbt.devtyme.android.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Int.toHours(): String {
    return if (this > 1) "${this}hrs"
    else "${this}hr"
}

fun Int.toMinutes(): String {
    return if (this > 1) "${this}mins"
    else "${this}min"
}
