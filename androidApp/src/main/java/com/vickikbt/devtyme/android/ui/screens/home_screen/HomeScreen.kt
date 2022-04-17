package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
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
import com.vickikbt.devtyme.android.ui.components.*
import com.vickikbt.devtyme.android.utils.toHours
import com.vickikbt.devtyme.android.utils.toMinutes
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.getCurrentUserProfile()
        viewModel.getTimeOfDay()
        viewModel.getDaysOfWeek()
        viewModel.getSummaries()
        viewModel.getDailyGoal()
    }

    val currentUserProfile = viewModel.currentUser.observeAsState().value
    val greetingMessage = viewModel.greetingMessage.observeAsState().value
    val currentDate = viewModel.currentDate.observeAsState().value
    val daysOfWeek = viewModel.daysOfWeek.observeAsState()
    val summaries = viewModel.summaries.observeAsState().value?.summary
    val summary = summaries?.get(0)
    val dailyGoal = viewModel.dailyGoal.observeAsState().value

    val scrollState: ScrollState = rememberScrollState()
    var selectedDate by remember { mutableStateOf(daysOfWeek.value?.indexOf(currentDate)) }

    var showDailyGoalDialog by remember { mutableStateOf(false) }

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
            daysOfWeek.value?.let {
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
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                if (dailyGoal == null || dailyGoal == 0L) {
                    //region Set Daily Goal
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        onClick = { showDailyGoalDialog = !showDailyGoalDialog },
                        contentPadding = PaddingValues(vertical = 8.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = MaterialTheme.colors.onPrimary
                        )
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Bottom),
                            text = stringResource(R.string.set_daily_goal).uppercase(Locale.getDefault()),
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                    //endregion
                } else {
                    //region Daily Goal
                    summary?.grandTotal?.let {
                        val progress by remember {
                            mutableStateOf((it.hours?.toDouble() ?: 0.0) / dailyGoal)
                        }
                        val animatedProgress = animateFloatAsState(
                            targetValue = progress.toFloat(),
                            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                        ).value

                        Text(
                            text = stringResource(R.string.title_daily_goal),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 22.sp,
                            style = MaterialTheme.typography.h5,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            elevation = 0.dp,
                            shape = RoundedCornerShape(6.dp),
                            backgroundColor = colorResource(id = R.color.cardBackground)
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
                                    text = "Worked ${it.hours?.toHours()} ${it.minutes?.toMinutes()} out of ${dailyGoal.toHours()}",
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
                                        .clip(RoundedCornerShape(10.dp))
                                        .constrainAs(linearProgressIndicator) {
                                            start.linkTo(parent.start)
                                            bottom.linkTo(parent.bottom)
                                            end.linkTo(imageProgress.start)
                                            width = Dimension.fillToConstraints
                                        },
                                    progress = animatedProgress,
                                    color = MaterialTheme.colors.secondary,
                                    backgroundColor = MaterialTheme.colors.primary.copy(.6f)
                                )
                            }
                        }
                    }
                    //endregion
                }

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
                        .fillMaxWidth(),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(6.dp),
                    backgroundColor = MaterialTheme.colors.primary.copy(alpha = .2f)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 16.dp
                        )
                    ) {
                        val grandTotalHours = arrayListOf<Float>()
                        summaries?.forEach { summary ->
                            Napier.e("Summary: $summary")
                            val timeInFloat = (summary.grandTotal?.hours)!!.toFloat()
                            grandTotalHours.add(timeInFloat)
                        }

                        Napier.e("Grand total hours: $grandTotalHours")

                        ChartWeeklyProgress(
                            hoursWorked = grandTotalHours,
                            daysOfWeek = daysOfWeek.value,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(270.dp)
                        )
                    }
                }
                //endregion

                //region Work Overview
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.title_work_overview),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h5,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    summary?.categories?.filter { it.hours != 0 || it.minutes != 0 }
                        ?.forEach { category ->
                            ItemProjectOverview(
                                title = category.name ?: "",
                                hours = "${category.hours?.toHours()} ${category.minutes?.toMinutes()}"
                            )
                        }
                }
                //endregion

                //region Projects
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.title_projects),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h5,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    summary?.projects?.filter { it.hours != 0 || it.minutes != 0 }
                        ?.forEach { project ->
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
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h5,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    summary?.languages?.filter { it.hours != 0 || it.minutes != 0 }
                        ?.forEach { language ->
                            ItemProjectOverview(
                                title = language.name ?: "",
                                hours = "${language.hours}hr ${language.minutes}mins"
                            )
                        }
                }
                //endregion

                if (showDailyGoalDialog) {
                    DialogDailyGoal(
                        showDialog = showDailyGoalDialog,
                        dailyGoal = dailyGoal?.toInt() ?: 0,
                        onPositiveActionClicked = {
                            viewModel.saveDailyGoal(hours = it)
                            showDailyGoalDialog = false
                        },
                        onNegativeActionClicked = { showDailyGoalDialog = false }
                    )
                }
            }
        }
    }
}
