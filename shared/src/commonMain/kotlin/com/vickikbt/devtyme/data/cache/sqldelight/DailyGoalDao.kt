package com.vickikbt.devtyme.data.cache.sqldelight

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.vickikbt.devtyme.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.map

class DailyGoalDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = appDatabase.appDatabaseQueries

    /**
     * Save daily goal value from user input to SQLDelight database
     */
    fun saveDailyGoal(
        dailyGoal: Long
    ) {
        dbQuery.transaction {
            dbQuery.saveDailyGoal(dailyGoal = dailyGoal)
        }
    }

    /**
     * Returns all data store in daily goal entity table in SQLDelight database
     * as a flow
     */
    val getDailyGoal = dbQuery.getDailyGoal().asFlow().map { it.executeAsOneOrNull()?.dailyGoal }
}
