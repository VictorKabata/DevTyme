package com.vickikbt.devtyme.domain.models

data class Summaries(
    val summary: List<Summary>,

    val end: String? = null,

    val start: String
)
