package com.vickikbt.devtyme.domain.utils

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

fun LocalDate.daysShift(days: Int): LocalDate = when {
    days < 0 -> {
        minus(DateTimeUnit.DayBased(-days))
    }
    days > 0 -> {
        plus(DateTimeUnit.DayBased(days))
    }
    else -> this
}
