package com.janiplayer.ui.performance

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable

@Composable
fun Modifier.optimizedClickable(onClick: () -> Unit): Modifier {
    return this.clickable(
        interactionSource = MutableInteractionSource(),
        indication = rememberRipple(radius = 18.dp),
        onClick = onClick
    )
}
