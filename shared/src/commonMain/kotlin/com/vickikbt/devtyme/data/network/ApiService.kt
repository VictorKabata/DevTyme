package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.models.Summaries

interface ApiService {

    suspend fun fetchUserToken(code: String): AccessTokenDto?

    suspend fun getCurrentUser(): CurrentUserDto?

    suspend fun fetchSummaries(start: String? = null, range: String? = null): Summaries?
}
