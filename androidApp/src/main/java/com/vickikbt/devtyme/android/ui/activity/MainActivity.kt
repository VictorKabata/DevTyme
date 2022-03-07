package com.vickikbt.devtyme.android.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vickikbt.devtyme.android.ui.navigation.Navigation
import com.vickikbt.devtyme.android.ui.theme.DevTymeTheme
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // installSplashScreen()

        setContent {
            DevTymeTheme {
                MainScreen()
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
private fun MainScreen(viewModel: MainActivityViewModel = getViewModel()) {
    val navController = rememberAnimatedNavController()
    val userAccessToken by remember { mutableStateOf(viewModel.accessToken) }

    Scaffold(
        bottomBar = {}
    ) {
        Navigation(navController = navController, isLoggedIn = !userAccessToken.value?.accessToken.isNullOrEmpty())
    }
}

@Preview
@Composable
private fun Preview() {
    // MainScreen()
}
