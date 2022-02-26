package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import com.vickikbt.devtyme.domain.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun fetchUserToken(code: String): AccessTokenDto {
        lateinit var tokenInfo: AccessTokenDto
        var refreshTokenInfo: AccessTokenDto

        httpClient.config {
            install(Auth) {

                bearer {
                    /**
                     * Specifies how to get the initial token
                     */
                    loadTokens {
                        tokenInfo = httpClient.submitForm<AccessTokenDto>(
                            url = "https://wakatime.com/oauth/token",
                            formParameters = Parameters.build {
                                append("client_id", Constants.CLIENT_ID)
                                append("client_secret", Constants.CLIENT_SECRET)
                                append("redirect_uri", Constants.REDIRECT_URL)
                                append("grant_type", "authorization_code")
                                append("code", code)
                            }
                        )
                        BearerTokens(
                            accessToken = tokenInfo.accessToken,
                            refreshToken = tokenInfo.refreshToken
                        )
                    }

                    /**
                     * Invoked when the endpoint receives 401 Http Response (Unauthorized)
                     */
                    refreshTokens { unauthorizedResponse: HttpResponse ->
                        refreshTokenInfo = httpClient.submitForm<AccessTokenDto>(
                            url = "https://wakatime.com/oauth/token",
                            formParameters = Parameters.build {
                                append("client_id", Constants.CLIENT_ID)
                                append("client_secret", Constants.CLIENT_SECRET)
                                append("redirect_uri", Constants.REDIRECT_URL)
                            }
                        )
                        BearerTokens(
                            accessToken = refreshTokenInfo.accessToken,
                            refreshToken = tokenInfo.refreshToken
                        )
                    }
                }
            }
        }

        return tokenInfo
    }
}
