package com.vickikbt.devtyme.data.repository

import com.vickikbt.devtyme.domain.models.AccessToken

interface AuthRepository {

    suspend fun fetchUserToken(code: String): AccessToken
}
