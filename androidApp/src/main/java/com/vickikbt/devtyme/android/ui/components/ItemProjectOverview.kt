package com.vickikbt.devtyme.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemProjectOverview(modifier: Modifier = Modifier, title: String, hours: String) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.primary.copy(alpha = .2f)
    ) {

        Column(modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)) {
            Text(
                text = "Project title",
                color = MaterialTheme.colors.onSurface,
                fontSize = 20.sp,
                style = MaterialTheme.typography.h5,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Text(
                text = "4hrs 20mins",
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                style = MaterialTheme.typography.h3,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }
    }
}
