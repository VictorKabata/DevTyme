package com.vickikbt.devtyme.di

import com.vickikbt.devtyme.data.cache.realm.AccessTokenDao
import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import com.vickikbt.devtyme.data.data_sources.AuthRepositoryImpl
import com.vickikbt.devtyme.data.mappers.toDomain
import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.data.network.ApiServiceImpl
import com.vickikbt.devtyme.data.network.utils.TokenInterceptor
import com.vickikbt.devtyme.domain.models.AccessToken
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.realm.Configuration
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.dsl.module

val commonModule = module {

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        val token = provideToken(accessTokenDao = get())

        HttpClient {
            defaultRequest {
                header("Authorization", "Bearer ${token?.accessToken}")
            }

            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.e(tag = "Http Client", message = message)
                    }
                }
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
    single { TokenInterceptor(accessTokenDao = get()) }

    /**
     *Create instance of realm config need to
     * instantiate realm db instance that is
     * provided to DAOs through constructor injection
     */
    single<Configuration> {
        RealmConfiguration.Builder()
            .name("devtyme.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(schemaVersion = 1)
            .schema(setOf(AccessTokenEntity::class))
            .build()
    }
    single { Realm.open(configuration = get()) }
    single { AccessTokenDao(realm = get()) }

    single<AuthRepository> { AuthRepositoryImpl(apiService = get(), accessTokenDao = get()) }
}

private fun provideToken(accessTokenDao: AccessTokenDao): AccessToken? {
    var token: AccessToken? = null
    CoroutineScope(Dispatchers.Default).launch {
        token = accessTokenDao.getToken.first()?.toDomain()
    }

    return token
}
