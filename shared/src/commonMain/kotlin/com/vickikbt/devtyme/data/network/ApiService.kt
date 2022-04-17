package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.data.network.models.SummariesDto

interface ApiService {

    suspend fun fetchUserToken(code: String): AccessTokenDto?

    suspend fun getCurrentUser(): CurrentUserDto?

    suspend fun fetchSummaries(
        start: String? = null,
        end: String? = null,
        range: String? = null
    ): SummariesDto?
}
