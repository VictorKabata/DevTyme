package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SummaryDto(
    @SerialName("categories")
    val categories: List<CategoryDto>? = null,

    @SerialName("grand_total")
    val grandTotal: GrandTotalDto? = null,

    @SerialName("languages")
    val languages: List<LanguageDto>? = null,

    @SerialName("projects")
    val projects: List<ProjectDto>? = null,

    @SerialName("range")
    val range: RangeDto? = null
)
