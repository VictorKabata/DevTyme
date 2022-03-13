package com.vickikbt.devtyme.data.cache.realm

import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import io.realm.Realm
import io.realm.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccessTokenDao constructor(private val realm: Realm) {

    /**
     * Save access token returned from network call to Realm database
     */
    fun saveToken(tokenEntity: AccessTokenEntity) {
        CoroutineScope(context = Dispatchers.Default).launch {
            realm.write {
                copyToRealm(tokenEntity)
            }
        }
    }

    /**
     * Returns all data store in access token entity table in Realm database
     * as a flow
     */
    val getToken = realm.query<AccessTokenEntity>().first().asFlow()

    /**
     * Deletes all data in access token entity table in Realm database
     */
    fun deleteToken() = realm.query<AccessTokenEntity>().find().delete()
}
