package com.vickikbt.devtyme.android.ui.screens.home_screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vickikbt.devtyme.android.R
import com.vickikbt.devtyme.android.ui.components.DatesTabs
import com.vickikbt.devtyme.android.ui.components.HomeToolbar
import com.vickikbt.devtyme.android.ui.components.ItemProjectOverview
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.getCurrentUserProfile()
        viewModel.getTimeOfDay()
        viewModel.getDaysOfWeek()
        viewModel.getSummaries()
    }

    val currentUserProfile = viewModel.currentUser.observeAsState().value
    val greetingMessage = viewModel.greetingMessage.observeAsState().value
    val currentDate = viewModel.currentDate.observeAsState().value
    val daysOfWeek = viewModel.daysOfWeek.observeAsState().value
    val summaries = viewModel.summaries.observeAsState().value?.summary?.get(0)

    var selectedDate by remember { mutableStateOf(0) }
    val scrollState: ScrollState = rememberScrollState()

    val lottieAnimation =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.trophy))
    val lottieAnimationSpec = animateLottieCompositionAsState(
        composition = lottieAnimation.value,
        isPlaying = true,
        iterations = 6,
        speed = 1.0f
    )

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

                        LottieAnimation(
                            modifier = Modifier
                                .size(130.dp)
                                .constrainAs(imageProgress) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                            composition = lottieAnimation.value,
                            progress = lottieAnimationSpec.value
                        )

                        Text(
                            modifier = Modifier.constrainAs(textProgress) {
                                start.linkTo(linearProgressIndicator.start)
                                bottom.linkTo(linearProgressIndicator.top)
                                end.linkTo(linearProgressIndicator.end)
                                width = Dimension.fillToConstraints
                            },
                            text = "Worked ${summaries?.grandTotal?.hours}hrs out of 8hrs",
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
                            progress = (summaries?.grandTotal?.hours ?: 0.1 / 8).toFloat(),
                            color = MaterialTheme.colors.secondary,
                            backgroundColor = MaterialTheme.colors.primary.copy(.2f),
                        )

                        // Napier.e("Progress: ${(summaries?.grandTotal?.hours ?: 0.1 / 8).toFloat()}")
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
                    summaries?.categories?.forEach { category ->
                        Napier.e("Category: ${category.name}: ${category.digital}")
                        ItemProjectOverview(
                            title = category.name ?: "",
                            hours = "${category.hours}hr ${category.minutes}mins"
                        )
                    }
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
                    summaries?.projects?.forEach { project ->
                        ItemProjectOverview(
                            title = project.name ?: "",
                            hours = "${project.hours}hr ${project.minutes}mins"
                        )
                    }
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
                    summaries?.languages?.forEach { language ->
                        ItemProjectOverview(
                            title = language.name ?: "",
                            hours = "${language.hours}hr ${language.minutes}mins"
                        )
                    }
                }
                //endregion
            }
        }
    }
}
