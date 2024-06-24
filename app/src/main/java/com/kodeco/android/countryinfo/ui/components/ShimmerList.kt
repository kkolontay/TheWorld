package com.kodeco.android.countryinfo.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerListItem(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .shimmerEffect())
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(20.dp)
                .shimmerEffect())
        }
        Spacer(modifier = Modifier.width(24.dp))
        Box(modifier = Modifier
            .size(40.dp)
            .shimmerEffect())
    }
}

@Composable
fun ShimmerList() {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item(20) {
            ShimmerListItem(modifier = Modifier.padding(16.dp))
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "remember")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ),
        label = "state"
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFE7E7E7),
                Color(0xFFB1B0B0),
                Color(0xFFE7E7E7)
            ),
            start = Offset(startOffsetX, y = 0f),
            end = Offset(startOffsetX + size.width.toFloat(), y = size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}