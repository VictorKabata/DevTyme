package com.vickikbt.devtyme.di

import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.data.network.ApiServiceImpl
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module

val commonModule = module {

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) { serializer = KotlinxSerializer() }
        }
    }
    single<ApiService> { ApiServiceImpl(httpClient = get()) }

    /**
     *Create instance of realm config need to
     * instantiate realm db instance that is
     * provided to DAOs through constructor injection
     */
    /*single { RealmConfiguration.with(schema = setOf(TokenEntity::class)) }
    single { Realm.open(configuration = get()) }
    single { TokenDao(appDatabase = get()) }*/
}
