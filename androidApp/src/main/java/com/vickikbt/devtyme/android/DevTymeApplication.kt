package com.vickikbt.devtyme.android

import android.app.Application
import com.vickikbt.devtyme.android.di.presentationModule
import com.vickikbt.devtyme.di.initKoin
import com.vickikbt.devtyme.domain.utils.NapierInit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

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
            NapierInit().init()
        }
    }
}
