package com.vickikbt.devtyme.data.network.utils

import com.vickikbt.devtyme.data.cache.realm.AccessTokenDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class TokenInterceptor constructor(private val accessTokenDao: AccessTokenDao) {

    val accessToken = runBlocking { accessTokenDao.getToken.first()?.accessToken }

    operator fun invoke() = accessToken ?: ""
}
