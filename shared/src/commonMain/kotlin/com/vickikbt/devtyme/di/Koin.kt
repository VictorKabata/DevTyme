package com.vickikbt.devtyme.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    val sharedModules = listOf(commonModule, platformModule())

    appDeclaration()
    modules(sharedModules)
}

/**
 * Called by iOS etc
 */
fun initKoin() = initKoin {}
