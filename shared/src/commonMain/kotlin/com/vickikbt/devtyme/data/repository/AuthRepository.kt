package com.vickikbt.devtyme.data.repository

import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import com.vickikbt.devtyme.domain.models.AccessToken
import io.realm.RealmResults
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun fetchUserToken(code: String): AccessToken?

    suspend fun saveUserToken(accessToken: AccessTokenEntity)

    suspend fun getUserToken(): Flow<RealmResults<AccessTokenEntity>>

    // suspend fun deleteUserToken()
}
