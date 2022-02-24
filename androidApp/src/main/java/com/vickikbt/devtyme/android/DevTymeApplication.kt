package com.vickikbt.devtyme.android

import android.app.Application
import com.vickikbt.devtyme.android.di.presentationModule
import com.vickikbt.devtyme.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import timber.log.Timber

class DevTymeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(presentationModule)

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@DevTymeApplication)
            modules(appModules)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            // Napier.base()
        }
    }
}
