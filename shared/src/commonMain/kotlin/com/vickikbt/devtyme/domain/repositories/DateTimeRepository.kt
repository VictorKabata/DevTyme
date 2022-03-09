package com.vickikbt.devtyme.domain.repositories

import kotlinx.coroutines.flow.Flow

interface DateTimeRepository {

    fun getTimeOfDay(): Flow<String>

    fun getCurrentDate(): Flow<String>

    fun getDaysOfWeek(): Flow<List<String>>
}
