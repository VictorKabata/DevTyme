package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import io.ktor.client.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun authenticateUser(code: String) {
        httpClient.config {
            install(Auth) {

                lateinit var tokenInfo: AccessTokenDto
                var refreshTokenInfo: AccessTokenDto

                bearer {
                    /**
                     * Specifies how to get the initial token
                     */
                    loadTokens {
                        tokenInfo = httpClient.submitForm<AccessTokenDto>(
                            url = "https://wakatime.com/oauth/token",
                            formParameters = Parameters.build {
                                append("client_id", "A0ijvQjx34y7GoMRqm9hW0VV")
                                append(
                                    "client_secret",
                                    "sec_NMqdYxpuCS73WSySycD2ciCoWSuamGzqS9ZMvSldud4qciyhe9sMFhNM39jrsDboCoeo6Eb6zDNsRgaI"
                                )
                                append("redirect_uri", "devtyme://oauth2")
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
                                append("client_id", "A0ijvQjx34y7GoMRqm9hW0VV")
                                append(
                                    "client_secret",
                                    "sec_NMqdYxpuCS73WSySycD2ciCoWSuamGzqS9ZMvSldud4qciyhe9sMFhNM39jrsDboCoeo6Eb6zDNsRgaI"
                                )
                                append("redirect_uri", "devtyme://oauth2")
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
    }

}
