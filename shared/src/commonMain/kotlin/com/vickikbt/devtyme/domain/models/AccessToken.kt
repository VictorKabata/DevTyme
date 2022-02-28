package com.vickikbt.devtyme.domain.models

data class AccessToken(
    var accessToken: String? = null,

    var expiresIn: Double? = null,

    var refreshToken: String? = null,

    var scope: String? = null,

    var tokenType: String? = null,

    var uid: String? = null,

    var createdAt: Float? = null
)
