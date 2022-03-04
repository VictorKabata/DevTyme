package com.vickikbt.devtyme.domain.models

data class User(
    val bio: String? = null,

    val colorScheme: String? = null,

    val createdAt: String? = null,

    val dateFormat: String? = null,

    val defaultDashboardRange: String? = null,

    val displayName: String? = null,

    val durationsSliceBy: String? = null,

    val email: String? = null,

    val fullName: String? = null,

    val hasPremiumFeatures: Boolean? = null,

    val humanReadableWebsite: String? = null,

    val id: String,

    val isEmailConfirmed: Boolean? = null,

    val isEmailPublic: Boolean? = null,

    val isHireable: Boolean? = null,

    val isOnboardingFinished: Boolean? = null,

    val languagesUsedPublic: Boolean? = null,

    val lastHeartbeatAt: String? = null,

    val lastPlugin: String? = null,

    val lastPluginName: String? = null,

    val lastProject: String? = null,

    val location: String? = null,

    val loggedTimePublic: Boolean? = null,

    val modifiedAt: String? = null,

    val needsPaymentMethod: Boolean? = null,

    val photo: String? = null,

    val photoPublic: Boolean? = null,

    val plan: String? = null,

    val publicEmail: String? = null,

    val publicProfileTimeRange: String? = null,

    val showMachineNameIp: Boolean? = null,

    val timeout: Int? = null,

    val timezone: String? = null,

    val username: String? = null,

    val website: String? = null,

    val weekdayStart: Int? = null,

    val writesOnly: Boolean? = null
)
