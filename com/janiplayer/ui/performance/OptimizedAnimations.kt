package com.janiplayer.ui.performance

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable

@Composable
fun optimizedFade(visible: Boolean): Float {
    return animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(150)
    ).value
}
