package com.vickikbt.devtyme.domain.models

data class Summary(
    val categories: List<Category>? = null,

    val grandTotal: GrandTotal? = null,

    val languages: List<Language>? = null,

    val projects: List<Project>? = null,

    val range: Range? = null
)
