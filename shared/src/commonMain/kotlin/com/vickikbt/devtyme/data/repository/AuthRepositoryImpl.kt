package com.vickikbt.devtyme.data.repository

import com.vickikbt.devtyme.data.mappers.toDomain
import com.vickikbt.devtyme.data.mappers.toEntity
import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.domain.models.AccessToken
import io.github.aakira.napier.Napier

class AuthRepositoryImpl constructor(
    private val apiService: ApiService
    // private val accessTokenDao: AccessTokenDao
) : AuthRepository {

    override suspend fun fetchUserToken(code: String): AccessToken? {
        val responseDto = apiService.fetchUserToken(code = code)
        val responseEntity = responseDto?.toEntity()

        // saveUserToken(accessToken = responseEntity)

        Napier.e("User token: ${responseEntity?.toDomain()}")

        return responseEntity?.toDomain()
    }

    /*override suspend fun saveUserToken(accessToken: AccessTokenEntity) =
        accessTokenDao.saveToken(tokenEntity = accessToken)

    override suspend fun getUserToken() = accessTokenDao.getToken

    override suspend fun deleteUserToken() = accessTokenDao.deleteToken()*/
}
