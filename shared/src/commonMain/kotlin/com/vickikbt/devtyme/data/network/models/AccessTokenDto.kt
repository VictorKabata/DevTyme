package com.vickikbt.devtyme.data.network.models

data class AccessTokenDto(
    var accessToken: String,
    var expiresIn: Double,
    var refreshToken: String,
    var scope: String,
    var tokenType: String,
    var uid: String,
    var createdAt: Float
) {

    // Check for expiry condition
    fun isValid(currentTimeSec: Float) = createdAt + expiresIn > currentTimeSec
}
