package com.vickikbt.devtyme.domain.utils

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.vickikbt.devtyme.data.cache.sqldelight.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(schema = AppDatabase.Schema, context, name = "DevTyme.db")
    }
}