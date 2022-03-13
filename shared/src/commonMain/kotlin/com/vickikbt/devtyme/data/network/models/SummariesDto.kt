package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SummariesDto(

    @SerialName("data")
    val summary: List<SummaryDto>? = null,

    @SerialName("end")
    val end: String?=null,

    @SerialName("start")
    val start: String?=null
)
