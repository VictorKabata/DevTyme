package com.vickikbt.devtyme.data.cache.realm.models

import io.realm.RealmObject

open class AccessTokenEntity : RealmObject {
    var accessToken: String? = null

    var expiresIn: Double? = null

    var refreshToken: String? = null

    var scope: String? = null

    var tokenType: String? = null

    var uid: String? = null

    var createdAt: Float? = null
}
