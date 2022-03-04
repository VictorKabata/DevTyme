package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vickikbt.devtyme.android.R
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    viewModel.getCurrentUserProfile()

    val currentUserProfile by remember { mutableStateOf(viewModel.currentUser) }
    Napier.e("Current user; $currentUserProfile")

    Box {
        Text(
            text = stringResource(R.string.title_home).uppercase(Locale.getDefault()),
            fontSize = 16.sp,
            style = MaterialTheme.typography.h5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
