package com.vickikbt.devtyme.data.repository

import com.vickikbt.devtyme.domain.models.AccessToken

interface AuthRepository {

    suspend fun fetchUserToken(code: String): AccessToken?

    // suspend fun saveUserToken(accessToken: AccessTokenEntity)

    // suspend fun getUserToken(): Flow<RealmResults<AccessTokenEntity>>

    // suspend fun deleteUserToken()
}
