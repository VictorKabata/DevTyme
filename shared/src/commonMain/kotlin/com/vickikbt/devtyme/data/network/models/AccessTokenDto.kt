package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenDto(
    @SerialName("access_token")
    var accessToken: String? = null,

    @SerialName("expires_in")
    var expiresIn: Double? = null,

    @SerialName("refresh_token")
    var refreshToken: String? = null,

    @SerialName("scope")
    var scope: String? = null,

    @SerialName("token_type")
    var tokenType: String? = null,

    @SerialName("uid")
    var uid: String,

    @SerialName("created_at")
    var createdAt: Float? = null
)
