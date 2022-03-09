package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.vickikbt.devtyme.android.ui.components.ItemProjectOverview
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
    val scrollState: ScrollState = rememberScrollState()

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
            daysOfWeek?.let {
                DatesTabs(
                    modifier = Modifier.fillMaxWidth(),
                    dates = it,
                    selectedTab = selectedDate,
                    onTabItemClick = { selectedDate = it }
                )
            }
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(state = scrollState)
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
                    elevation = 0.dp,
                    shape = RoundedCornerShape(6.dp),
                    backgroundColor = MaterialTheme.colors.primary.copy(alpha = .2f)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 10.dp
                        )
                    ) {
                        val (textProgressTitle, textProgressDescription, imageProgress, textProgress, linearProgressIndicator) = createRefs()

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

                        Text(
                            modifier = Modifier.constrainAs(textProgress) {
                                start.linkTo(linearProgressIndicator.start)
                                bottom.linkTo(linearProgressIndicator.top)
                                end.linkTo(linearProgressIndicator.end)
                                width = Dimension.fillToConstraints
                            },
                            text = "Worked 6hrs out of 8hrs",
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 13.sp,
                            style = MaterialTheme.typography.h4,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            modifier = Modifier
                                .height(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .constrainAs(linearProgressIndicator) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(imageProgress.start)
                                    width = Dimension.fillToConstraints
                                },
                            progress = .5f,
                            color = MaterialTheme.colors.secondary,
                            backgroundColor = MaterialTheme.colors.primary.copy(.2f)
                        )
                    }
                }
                //endregion

                //region Weekly Progress
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.title_weekly_progress),
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
                        .height(350.dp),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(6.dp),
                    backgroundColor = MaterialTheme.colors.primary.copy(alpha = .2f)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 10.dp
                        )
                    ) {
                        // ToDo: Weekly stats
                    }
                }
                //endregion

                //region Work Overview
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.title_work_overview),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    ItemProjectOverview(title = "Design", hours = "4hrs 20mins")
                    ItemProjectOverview(title = "Coding", hours = "6hrs 90mins")
                }
                //endregion

                //region Projects
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.title_projects),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    ItemProjectOverview(title = "DevTyme", hours = "4hrs 20mins")
                    ItemProjectOverview(title = "Notflix", hours = "6hrs 90mins")
                }
                //endregion

                //region Languages
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.title_languages),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    ItemProjectOverview(title = "Kotlin", hours = "4hrs 20mins")
                    ItemProjectOverview(title = "Golang", hours = "6hrs 90mins")
                    ItemProjectOverview(title = "Java", hours = "6hrs 90mins")
                }
                //endregion
            }
        }
    }
}
