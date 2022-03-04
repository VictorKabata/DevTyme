package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("bio")
    val bio: String? = null,

    @SerialName("color_scheme")
    val colorScheme: String? = null,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("date_format")
    val dateFormat: String? = null,

    @SerialName("default_dashboard_range")
    val defaultDashboardRange: String? = null,

    @SerialName("display_name")
    val displayName: String? = null,

    @SerialName("durations_slice_by")
    val durationsSliceBy: String? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("full_name")
    val fullName: String? = null,

    @SerialName("has_premium_features")
    val hasPremiumFeatures: Boolean? = null,

    @SerialName("human_readable_website")
    val humanReadableWebsite: String? = null,

    @SerialName("id")
    val id: String,

    @SerialName("is_email_confirmed")
    val isEmailConfirmed: Boolean? = null,

    @SerialName("is_email_public")
    val isEmailPublic: Boolean? = null,

    @SerialName("is_hireable")
    val isHireable: Boolean? = null,

    @SerialName("is_onboarding_finished")
    val isOnboardingFinished: Boolean? = null,

    @SerialName("languages_used_public")
    val languagesUsedPublic: Boolean? = null,

    @SerialName("last_heartbeat_at")
    val lastHeartbeatAt: String? = null,

    @SerialName("last_plugin")
    val lastPlugin: String? = null,

    @SerialName("last_plugin_name")
    val lastPluginName: String? = null,

    @SerialName("last_project")
    val lastProject: String? = null,

    @SerialName("location")
    val location: String? = null,

    @SerialName("logged_time_public")
    val loggedTimePublic: Boolean? = null,

    @SerialName("modified_at")
    val modifiedAt: String? = null,

    @SerialName("needs_payment_method")
    val needsPaymentMethod: Boolean? = null,

    @SerialName("photo")
    val photo: String? = null,

    @SerialName("photo_public")
    val photoPublic: Boolean? = null,

    @SerialName("plan")
    val plan: String? = null,

    @SerialName("public_email")
    val publicEmail: String? = null,

    @SerialName("public_profile_time_range")
    val publicProfileTimeRange: String? = null,

    @SerialName("show_machine_name_ip")
    val showMachineNameIp: Boolean? = null,

    @SerialName("timeout")
    val timeout: Int? = null,

    @SerialName("timezone")
    val timezone: String? = null,

    @SerialName("username")
    val username: String? = null,

    @SerialName("website")
    val website: String? = null,

    @SerialName("weekday_start")
    val weekdayStart: Int? = null,

    @SerialName("writes_only")
    val writesOnly: Boolean? = null
)
