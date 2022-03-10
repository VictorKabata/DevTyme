package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("digital")
    val digital: String? = null,

    @SerialName("hours")
    val hours: Int? = null,

    @SerialName("minutes")
    val minutes: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("percent")
    val percent: Double? = null,

    @SerialName("seconds")
    val seconds: Int? = null,

    @SerialName("text")
    val text: String? = null,

    @SerialName("total_seconds")
    val totalSeconds: Double? = null
)
