package com.vickikbt.devtyme.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vickikbt.devtyme.android.R

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {
    object Login : NavigationItem("login", R.string.title_login, null)
    object Home : NavigationItem("home", R.string.title_home, null)

    object WebView : NavigationItem("webview", R.string.title_webview, null)
}
