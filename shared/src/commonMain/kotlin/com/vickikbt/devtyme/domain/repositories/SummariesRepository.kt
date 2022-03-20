package com.vickikbt.devtyme.domain.repositories

import com.vickikbt.devtyme.domain.models.Summaries
import kotlinx.coroutines.flow.Flow

interface SummariesRepository {

    suspend fun fetchSummaries(start: String? = null, range: String? = null): Flow<Summaries?>

    suspend fun saveDailyGoal(hours: Long)

    suspend fun getDailyGoal(): Flow<Long?>
}
