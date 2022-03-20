package com.vickikbt.devtyme.data.mappers

import com.vickikbt.devtyme.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.devtyme.data.network.models.AccessTokenDto

internal fun AccessTokenDto.toEntity(): AccessTokenEntity {
    return AccessTokenEntity(
        accessToken = this.accessToken,
        refreshToken = this@toEntity.refreshToken,
        scope = this.scope,
        tokenType = this.tokenType,
        uid = this.uid,
    )
}
