package com.vickikbt.devtyme.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ItemTabIndicator(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.height(56.dp), verticalArrangement = Arrangement.Center) {
        Box(
            modifier = modifier
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.secondary.copy(alpha = .4f))
        )
    }
}
