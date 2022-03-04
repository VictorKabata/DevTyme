package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import com.vickikbt.devtyme.data.network.models.CurrentUserDto

interface ApiService {

    suspend fun fetchUserToken(code: String): AccessTokenDto?

    suspend fun getCurrentUser(): CurrentUserDto?
}
