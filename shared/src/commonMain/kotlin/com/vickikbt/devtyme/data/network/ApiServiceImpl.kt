package com.vickikbt.devtyme.data.network

import io.ktor.client.*

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun authenticateUser() {
        // httpClient.get
    }
}
