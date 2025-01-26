package com.example.common_ui.utils

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.common_ui.theme.Grey
import com.example.common_ui.theme.LineDark
import com.example.common_ui.theme.WhiteGrey

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = "loading shimmer"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                WhiteGrey,
                Grey,
                LineDark,
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned { size = it.size }
}

@Composable
fun Modifier.showShimmer(showShimmer: Boolean, targetValue: Float = 1000f) = composed {
    val brushShimmer = if (showShimmer) {
        val shimmerColors = listOf(
            WhiteGrey,
            Grey,
            LineDark,
        )
        val transition = rememberInfiniteTransition("")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Reverse,
            ),
            label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                Color.Transparent,
                Color.Transparent
            ),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
    drawWithCache {
        onDrawBehind {
            drawRoundRect(brushShimmer)
        }
    }
}