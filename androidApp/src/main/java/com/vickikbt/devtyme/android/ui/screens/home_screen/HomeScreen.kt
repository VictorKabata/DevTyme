package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
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

    LaunchedEffect(key1 = true ){
        viewModel.getCurrentUserProfile()
    }

    val currentUserProfile by remember { mutableStateOf(viewModel.currentUser) }
    Napier.e("Current user: $currentUserProfile")
    Column {
        Text(
            text = stringResource(R.string.title_home).uppercase(Locale.getDefault()),
            fontSize = 16.sp,
            style = MaterialTheme.typography.h5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Text(
            text = currentUserProfile.value.toString(),
            fontSize = 16.sp,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}
