package com.vickikbt.devtyme.di

import com.vickikbt.devtyme.domain.utils.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }
}
