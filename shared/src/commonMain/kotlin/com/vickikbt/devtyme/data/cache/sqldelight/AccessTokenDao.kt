package com.vickikbt.devtyme.data.cache.sqldelight

import com.vickikbt.devtyme.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.flowOf

class AccessTokenDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = appDatabase.appDatabaseQueries

    /**
     * Save access token returned from network call to SQLDelight database
     */
    fun saveToken(
        accessTokenEntity: AccessTokenEntity
    ) {
        dbQuery.transaction {
            dbQuery.saveToken(accessTokenEntity)
        }
    }

    /**
     * Returns all data store in access token entity table in SQLDelight database
     * as a flow
     */
    val getToken = flowOf(dbQuery.getToken().executeAsOneOrNull())

    /**
     * Deletes all data in access token entity table in SQLDelight database
     */
    fun deleteToken() = dbQuery.deleteToken()
}
