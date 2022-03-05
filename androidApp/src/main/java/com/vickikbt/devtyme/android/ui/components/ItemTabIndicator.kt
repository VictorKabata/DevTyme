package com.vickikbt.devtyme.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ItemTabIndicator(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .background(MaterialTheme.colors.secondary)
            .clip(RoundedCornerShape(10.dp))
    )
}
