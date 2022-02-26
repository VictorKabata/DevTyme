package com.vickikbt.devtyme.android.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vickikbt.devtyme.android.ui.theme.DevTymeTheme

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

@Composable
private fun MainScreen() {
}

@Preview
@Composable
private fun Preview() {
    MainScreen()
}
