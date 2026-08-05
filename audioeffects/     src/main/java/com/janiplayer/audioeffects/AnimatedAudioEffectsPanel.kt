package com.janiplayer.audioeffects

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

@Composable
fun AnimatedAudioEffectsPanel(
    visible: Boolean,
    state: EffectsState,
    onBandChange: (Int, Float) -> Unit,
    onPresetSelected: (String) -> Unit,
    onBassChange: (Float) -> Unit,
    onVirtualizerChange: (Float) -> Unit,
    onLoudnessChange: (Float) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 }
        ) + fadeIn() + scaleIn(initialScale = 0.95f),
        exit = slideOutVertically(
            targetOffsetY = { it / 2 }
        ) + fadeOut() + scaleOut(targetScale = 0.95f)
    ) {
        Surface(
            tonalElevation = 6.dp,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            AudioEffectsMainScreen(
                state = state,
                onBandChange = onBandChange,
                onPresetSelected = onPresetSelected,
                onBassChange = onBassChange,
                onVirtualizerChange = onVirtualizerChange,
                onLoudnessChange = onLoudnessChange
            )
        }
    }
}
