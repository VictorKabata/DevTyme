package com.vickikbt.devtyme.data.data_sources

import com.vickikbt.devtyme.domain.repositories.DateTimeRepository
import com.vickikbt.devtyme.domain.utils.daysShift
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeRepositoryImpl : DateTimeRepository {

    override fun getTimeOfDay(): Flow<String> {
        return when (
            Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault()).hour
        ) {
            in 0..11 -> flowOf("Good Morning,")
            in 12..15 -> flowOf("Good Afternoon,")
            in 16..21 -> flowOf("Good Evening,")
            else -> flowOf("Hey,")
        }
    }

    override fun getCurrentDate(): Flow<String> {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        return flowOf(currentDate.toString())
    }

    override fun getDaysOfWeek(): Flow<List<String>> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val days = mutableListOf<String>()
        val firstWeekDay = today.daysShift(-DayOfWeek.values().indexOf(today.dayOfWeek))
        for (i in 0 until DayOfWeek.values().count()) {
            days.add(firstWeekDay.daysShift(i).toString())
        }

        return flowOf(days)
    }
}
