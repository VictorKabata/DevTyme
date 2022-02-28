package com.vickikbt.devtyme.di

import com.vickikbt.devtyme.data.cache.realm.AccessTokenDao
import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.data.network.ApiServiceImpl
import com.vickikbt.devtyme.data.repository.AuthRepository
import com.vickikbt.devtyme.data.repository.AuthRepositoryImpl
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.realm.Realm
import io.realm.RealmConfiguration
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
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
    single<ApiService> { ApiServiceImpl(httpClient = get()) }

    /**
     *Create instance of realm config need to
     * instantiate realm db instance that is
     * provided to DAOs through constructor injection
     */
    single { RealmConfiguration.with(schema = setOf(AccessTokenEntity::class)) }
    single { Realm.open(configuration = get()) }
    single { AccessTokenDao(realm = get()) }

    single<AuthRepository> { AuthRepositoryImpl(apiService = get(), accessTokenDao = get()) }
}
