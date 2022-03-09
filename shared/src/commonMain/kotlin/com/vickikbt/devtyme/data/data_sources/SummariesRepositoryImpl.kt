package com.vickikbt.devtyme.data.data_sources

import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.domain.models.Summaries
import com.vickikbt.devtyme.domain.repositories.SummariesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SummariesRepositoryImpl constructor(private val apiService: ApiService) :
    SummariesRepository {

    override suspend fun fetchSummaries(start: String?, range: String?): Flow<Summaries?> {
        val response = apiService.fetchSummaries(start = start, range = range)
        return flowOf(response)
    }
}
