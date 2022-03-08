package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.vickikbt.devtyme.android.R
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
                title = "$greetingMessage ${
                currentUserProfile?.user?.displayName?.substringBefore(" ")
                    ?.trim() ?: currentUserProfile?.user?.username
                }",
                subTitle = "Let's make this day productive",
                profileImageUrl = currentUserProfile?.user?.photo ?: ""
            )
        }
    ) {
        Column {

            //region Dates Tabs
            val tabItems = listOf("S\n11", "M\n12", "T\n13", "W\n14", "T\n15", "F\n16", "S\n17")

            DatesTabs(
                modifier = Modifier.fillMaxWidth(),
                dates = tabItems,
                selectedTab = selectedDate,
                onTabItemClick = { selectedDate = it }
            )
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {

                //region Daily Goal
                Text(
                    text = stringResource(R.string.title_daily_goal),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(6.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    elevation = 1.dp,
                    shape = RoundedCornerShape(6.dp),
                    backgroundColor = MaterialTheme.colors.primary.copy(alpha = .2f)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 10.dp
                        )
                    ) {
                        val (textProgressTitle, textProgressDescription, imageProgress) = createRefs()

                        Text(
                            modifier = Modifier.constrainAs(textProgressTitle) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(imageProgress.start)
                                width = Dimension.fillToConstraints
                            },
                            text = "Almost There!",
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.h5,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            modifier = Modifier.constrainAs(textProgressDescription) {
                                start.linkTo(textProgressTitle.start)
                                top.linkTo(textProgressTitle.bottom)
                                end.linkTo(imageProgress.start)
                                width = Dimension.fillToConstraints
                            },
                            text = "Just keep going",
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.h4,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )

                        Image(
                            modifier = Modifier
                                .size(130.dp)
                                .constrainAs(imageProgress) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = ""
                        )
                    }
                }
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
}
