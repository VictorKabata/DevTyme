package com.vickikbt.devtyme.data.network.utils

import com.vickikbt.devtyme.data.cache.sqldelight.AccessTokenDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TokenInterceptor : KoinComponent {

    private val accessTokenDao: AccessTokenDao by inject()

    operator fun invoke() = runBlocking {
        "Bearer ${accessTokenDao.getToken.first()?.accessToken ?: ""}"
    }
}
