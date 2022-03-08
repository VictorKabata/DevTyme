package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.devtyme.android.ui.components.DatesTabs
import com.vickikbt.devtyme.android.ui.components.HomeToolbar
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.getCurrentUserProfile()
        viewModel.getTimeOfDay()
        viewModel.getDaysOfWeek()
    }

    val currentUserProfile = viewModel.currentUser.observeAsState().value
    val greetingMessage = viewModel.greetingMessage.observeAsState().value
    val daysOfWeek = viewModel.daysOfWeek.observeAsState().value

    var selectedDate by remember { mutableStateOf(0) }

    daysOfWeek?.forEach {
        Napier.e("Days of week: $it")
    }

    Scaffold(
        topBar = {
            HomeToolbar(
                title = "$greetingMessage ${currentUserProfile?.user?.displayName?.substringBefore(" ")?.trim() ?: currentUserProfile?.user?.username}",
                subTitle = "Let's make this day productive",
                profileImageUrl = currentUserProfile?.user?.photo ?: ""
            )
        }
    ) {
        Column {
            //region Dates Tabs
            val tabItems = listOf("S\n11", "M\n12", "T\n13", "W\n14", "T\n15", "F\n16", "S\n17")

            DatesTabs(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(bottom = 2.dp),
                dates = tabItems,
                selectedTab = selectedDate,
                onTabItemClick = { selectedDate = it }
            )

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
