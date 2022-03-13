package com.vickikbt.devtyme.domain.utils

object Constants {

    const val AUTH_BASE_URL = "https://wakatime.com/"
    const val BASE_URL = "https://wakatime.com/api/v1/"

    // ToDo: Move to config file
    const val CLIENT_ID = "A0ijvQjx34y7GoMRqm9hW0VV"
    const val CLIENT_SECRET =
        "sec_NMqdYxpuCS73WSySycD2ciCoWSuamGzqS9ZMvSldud4qciyhe9sMFhNM39jrsDboCoeo6Eb6zDNsRgaI"
    const val REDIRECT_URL = "devtyme://oauth2"
    val SCOPES = arrayOf(
        "email",
        "read_logged_time",
        "read_stats",
        "read_private_leaderboards"
    ).joinToString(",")

    val WEB_URL =
        "https://wakatime.com/oauth/authorize?client_id=$CLIENT_ID&response_type=code&redirect_uri=$REDIRECT_URL&scope=$SCOPES&force_approve=true"
}
