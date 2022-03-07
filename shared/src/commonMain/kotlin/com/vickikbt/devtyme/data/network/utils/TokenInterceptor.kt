package com.vickikbt.devtyme.data.network.utils

import com.vickikbt.devtyme.data.cache.realm.AccessTokenDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first

class TokenInterceptor constructor(private val accessTokenDao: AccessTokenDao) {

    private var token: String? = null

    init {
    }

    fun accessToken() = CoroutineScope(Dispatchers.Default).async {
        val response = accessTokenDao.getToken.first()?.accessToken
        token = response

        return@async response
    }

    suspend operator fun invoke() = CoroutineScope(Dispatchers.Default).async {
        return@async accessToken().await() ?: ""
    }.await()
}
