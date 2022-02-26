package com.vickikbt.devtyme.data.repository

import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.domain.models.AccessToken

class AuthRepositoryImpl constructor(private val apiService: ApiService) : AuthRepository {

    override suspend fun fetchUserToken(code: String): AccessToken {
        TODO("Not yet implemented")
    }
}
