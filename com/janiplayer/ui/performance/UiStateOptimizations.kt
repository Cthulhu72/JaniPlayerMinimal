package com.janiplayer.ui.performance

import androidx.compose.runtime.*

@Composable
fun <T> optimizedState(source: () -> T): State<T> {
    return remember {
        derivedStateOf { source() }
    }
}
