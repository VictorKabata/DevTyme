package com.vickikbt.devtyme.android.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vickikbt.devtyme.android.R
import java.util.*

@Composable
fun DialogDailyGoal(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    dailyGoal: Int,
    onPositiveActionClicked: (Int) -> Unit,
    onNegativeActionClicked: () -> Unit
) {

    val context = LocalContext.current
    var goal by remember { mutableStateOf(dailyGoal) }

    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Card(
                modifier = modifier,
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        FloatingActionButton(onClick = {
                            if (goal > 0) {
                                goal -= 1
                            }
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_minus),
                                contentDescription = "Reduce an hour"
                            )
                        }

                        Text(
                            modifier = Modifier.align(Alignment.Bottom),
                            text = goal.toString(),
                            fontSize = 74.sp,
                            style = MaterialTheme.typography.h6,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onSurface
                        )

                        FloatingActionButton(onClick = {
                            if (goal < 24) {
                                goal += 1
                            } else {
                                Toast.makeText(
                                    context,
                                    "Not available in your current planet",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "Add an hour"
                            )
                        }
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        onClick = { if (goal in 1..24) onPositiveActionClicked(goal) },
                        contentPadding = PaddingValues(vertical = 8.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = MaterialTheme.colors.onPrimary
                        ),
                        enabled = goal in 1..24
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Bottom),
                            text = stringResource(R.string.set).uppercase(Locale.getDefault()),
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }

                    Text(
                        modifier = Modifier.clickable { onNegativeActionClicked() },
                        text = stringResource(R.string.cancel),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h5,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}
