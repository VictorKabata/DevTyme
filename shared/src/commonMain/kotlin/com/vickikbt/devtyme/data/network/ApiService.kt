package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto

interface ApiService {

    suspend fun fetchUserToken(code: String): AccessTokenDto?
}
