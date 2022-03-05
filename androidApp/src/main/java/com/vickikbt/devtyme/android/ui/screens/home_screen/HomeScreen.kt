package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.vickikbt.devtyme.android.ui.components.HomeToolbar
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.getCurrentUserProfile()
    }

    val currentUserProfile = viewModel.currentUser.observeAsState().value

    Napier.e("Current user profile: $currentUserProfile")

    Scaffold(
        topBar = {
            HomeToolbar(
                title = currentUserProfile?.user?.displayName ?: "",
                subTitle = "Let's make this day productive",
                profileImageUrl = currentUserProfile?.user?.photo ?: ""
            )
        }
    ) {
        Column {
            //region Dates Tabs

            //endregion

            //region Daily Goal

            //endregion

            //region Weekly Progress

            //endregion

            //region Work Overview

            //endregion

            //region Projects

            //endregion

            //region Languages

            //endregion
        }
    }
}
