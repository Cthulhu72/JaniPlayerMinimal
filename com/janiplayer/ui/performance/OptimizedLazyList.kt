package com.janiplayer.ui.performance

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun <T> OptimizedLazyList(
    items: List<T>,
    keySelector: (T) -> Any,
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn {
        items(
            items = items,
            key = { keySelector(it) }
        ) { item ->
            itemContent(item)
        }
    }
}
