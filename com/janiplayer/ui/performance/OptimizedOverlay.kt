package com.janiplayer.ui.performance

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun OptimizedOverlay(
    alpha: Float,
    content: @Composable () -> Unit
) {
    Box(
        Modifier.graphicsLayer {
            this.alpha = alpha
        }
    ) {
        content()
    }
}
