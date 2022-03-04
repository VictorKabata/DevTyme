package com.vickikbt.devtyme.domain.repositories

import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.models.AccessToken
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun fetchUserToken(code: String)

    suspend fun saveUserToken(accessToken: AccessTokenEntity)

    suspend fun getUserToken(): Flow<AccessToken?>

    suspend fun deleteUserToken()

    suspend fun getUserProfile(): Flow<CurrentUserDto?>
}
