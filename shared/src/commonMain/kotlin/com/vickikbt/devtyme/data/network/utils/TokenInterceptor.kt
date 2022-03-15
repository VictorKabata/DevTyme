package com.vickikbt.devtyme.data.network.utils

import com.vickikbt.devtyme.data.cache.realm.AccessTokenDao
import io.realm.internal.platform.runBlocking
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TokenInterceptor : KoinComponent {

    private val accessTokenDao: AccessTokenDao by inject()

    operator fun invoke() = runBlocking {
        "Bearer ${accessTokenDao.getToken.first()?.accessToken ?: ""}"
    }
}
