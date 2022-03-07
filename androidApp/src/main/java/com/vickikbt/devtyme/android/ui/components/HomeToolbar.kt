package com.vickikbt.devtyme.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vickikbt.devtyme.android.R

@Composable
fun HomeToolbar(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    profileImageUrl: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .width(56.dp)
            .background(MaterialTheme.colors.primary)
            .padding(start = 16.dp, top = 4.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //region Title and Subtitle
        Column(modifier = Modifier.weight(.8f)) {
            Text(
                text = title,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Text(
                text = subTitle,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }
        //endregion

        //region Profile Picture
        val profileImagePainter = rememberImagePainter(data = profileImageUrl) {
            // placeholder(R.drawable.ic_logo)
            crossfade(true)
        }

        ItemCircleImage(
            modifier = Modifier
                .weight(.2f)
                .size(38.dp),
            image = profileImagePainter,
            contentDescription = stringResource(R.string.profile_image)
        )
        //endregion
    }
}

@Preview
@Composable
fun Preview() {
    HomeToolbar(title = "Hello", subTitle = "Victor", profileImageUrl = "")
}
