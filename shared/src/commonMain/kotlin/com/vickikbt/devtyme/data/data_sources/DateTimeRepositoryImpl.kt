package com.vickikbt.devtyme.data.data_sources

import com.vickikbt.devtyme.domain.repositories.DateTimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.*

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

    override fun getDaysOfWeek(): Flow<List<String>> {
        val range = Clock.System.now()
            .toLocalDateTime(timeZone = TimeZone.currentSystemDefault()).month

        val dates = DayOfWeek.values().map { it.name }

        return flowOf(dates)
    }
}
