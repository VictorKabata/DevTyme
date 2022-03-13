package com.vickikbt.devtyme.data.mappers

import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import com.vickikbt.devtyme.domain.models.AccessToken

internal fun AccessTokenEntity.toDomain(): AccessToken {
    return AccessToken(
        accessToken = this.accessToken,
        expiresIn = this.expiresIn,
        refreshToken = this.refreshToken,
        scope = this.scope,
        tokenType = this.tokenType,
        uid = this.uid,
        createdAt = this.createdAt
    )
}
