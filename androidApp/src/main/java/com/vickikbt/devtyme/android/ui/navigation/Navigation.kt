package com.vickikbt.devtyme.android.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vickikbt.devtyme.android.ui.screens.home_screen.HomeScreen
import com.vickikbt.devtyme.android.ui.screens.login_screen.LoginScreen
import com.vickikbt.devtyme.android.ui.screens.webview_screen.WebViewScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {

    // val defaultEnterAnimationDuration = 600
    // val defaultExitAnimationDuration = 1100
    // val slideDefaultInitialOffset = 1800
    // val slideDefaultTargetOffset = 1500

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(route = NavigationItem.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = NavigationItem.WebView.route) {
            WebViewScreen(navController = navController)
        }

        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}
