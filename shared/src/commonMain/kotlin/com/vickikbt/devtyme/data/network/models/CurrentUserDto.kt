package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName

data class CurrentUserDto(
    @SerialName("data")
    val user: UserDto
)
