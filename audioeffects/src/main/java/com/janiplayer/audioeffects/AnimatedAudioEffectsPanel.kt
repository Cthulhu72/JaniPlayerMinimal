package com.janiplayer.audioeffects

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedAudioEffectsPanel(
    visible: Boolean,
    state: EffectsState,
    accentColor: Color,
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
                accentColor = accentColor,
                onBandChange = onBandChange,
                onPresetSelected = onPresetSelected,
                onBassChange = onBassChange,
                onVirtualizerChange = onVirtualizerChange,
                onLoudnessChange = onLoudnessChange
            )
        }
    }
}
