package com.janiplayer.audioeffects

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AudioEffectsMainScreen(
    state: EffectsState,
    accentColor: Color,
    onBandChange: (Int, Float) -> Unit,
    onPresetSelected: (String) -> Unit,
    onBassChange: (Float) -> Unit,
    onVirtualizerChange: (Float) -> Unit,
    onLoudnessChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Audio Effects",
            style = MaterialTheme.typography.headlineSmall,
            color = accentColor,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PresetSelector(
            presets = state.presets,
            selectedPreset = state.preset,
            onPresetSelected = onPresetSelected,
            accentColor = accentColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            state.bands.forEachIndexed { index, band ->
                EqualizerBandSlider(
                    bandName = band.label,
                    gain = band.gain,
                    onGainChange = { onBandChange(index, it) },
                    accentColor = accentColor
                )
            }
        }

        EffectSlider(
            value = state.bassBoost,
            onValueChange = onBassChange,
            title = "Bass Boost",
            accentColor = accentColor
        )

        EffectSlider(
            value = state.virtualizer,
            onValueChange = onVirtualizerChange,
            title = "Virtualizer",
            accentColor = accentColor
        )

        EffectSlider(
            value = state.loudness,
            onValueChange = onLoudnessChange,
            title = "Loudness Enhancer",
            accentColor = accentColor
        )
    }
}
