package com.vickikbt.devtyme.data.mappers

import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import com.vickikbt.devtyme.data.network.models.AccessTokenDto

internal fun AccessTokenDto.toEntity(): AccessTokenEntity {
    return AccessTokenEntity().apply {
        accessToken = this@toEntity.accessToken
        expiresIn = this@toEntity.expiresIn
        refreshToken = this@toEntity.refreshToken
        scope = this@toEntity.scope
        tokenType = this@toEntity.tokenType
        uid = this@toEntity.uid
        createdAt = this@toEntity.createdAt
    }
}
