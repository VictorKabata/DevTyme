package com.vickikbt.devtyme.android.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vickikbt.devtyme.android.ui.navigation.Navigation
import com.vickikbt.devtyme.android.ui.theme.DevTymeTheme

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
private fun MainScreen() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        bottomBar = {}
    ) {
        Navigation(navController = navController)
    }
}

@Preview
@Composable
private fun Preview() {
    // MainScreen()
}
