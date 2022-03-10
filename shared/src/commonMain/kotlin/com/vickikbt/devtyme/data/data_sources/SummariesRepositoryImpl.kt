package com.vickikbt.devtyme.data.data_sources

import com.russhwolf.settings.Settings
import com.vickikbt.devtyme.data.mappers.toDomain
import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.domain.models.Summaries
import com.vickikbt.devtyme.domain.repositories.SummariesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SummariesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val settings: Settings
) : SummariesRepository {

    override suspend fun fetchSummaries(start: String?, range: String?): Flow<Summaries?> {
        val response = apiService.fetchSummaries(start = start, range = range)
        return flowOf(response?.toDomain())
    }

    override suspend fun saveDailyGoal(hours: Int) =
        settings.putInt(key = "daily_goal", value = hours)

    override suspend fun getDailyGoal(): Flow<Int?> {
        val hours = settings.getIntOrNull(key = "daily_goal")
        return flowOf(hours)
    }
}
