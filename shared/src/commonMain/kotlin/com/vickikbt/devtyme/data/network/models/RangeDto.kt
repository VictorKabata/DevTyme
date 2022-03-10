package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RangeDto(
    @SerialName("date")
    val date: String? = null,

    @SerialName("end")
    val end: String? = null,

    @SerialName("start")
    val start: String? = null,

    @SerialName("text")
    val text: String? = null,

    @SerialName("timezone")
    val timezone: String? = null
)
