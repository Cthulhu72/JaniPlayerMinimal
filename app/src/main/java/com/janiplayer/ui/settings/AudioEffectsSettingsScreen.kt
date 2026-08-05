package com.janiplayer.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.janiplayer.audioeffects.AudioEffectsDataStore
import com.janiplayer.audioeffects.AudioEffectsViewModel
import com.janiplayer.audioeffects.AudioEffectsMainScreen

Compo@Composable
fun AudioEffectsSettingsScreen(
    vm: DspViewModel,
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

            Slider(
    value = state.eqBands[bandIndex].toFloat(),
    onValueChange = { vm.onEqBandChange(bandIndex, it.toInt().toShort()) },
    valueRange = -1500f..1500f
            Slider(
    value = state.bass.toFloat(),
    onValueChange = { vm.onBassChange(it.toInt().toShort()) },
    valueRange = 0f..1000f
            Slider(
    value = state.virtualizer.toFloat(),
    onValueChange = { vm.onVirtualizerChange(it.toInt().toShort()) },
    valueRange = 0f..1000f
            Slider(
    value = state.loudness.toFloat(),
    onValueChange = { vm.onLoudnessChange(it.toInt()) },
    valueRange = 0f..1500f
)
)
)
)
    ) {

        Text(
            text = "Audio Effects",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // EQ sávok
        Text("Equalizer", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        state.eqBands.forEachIndexed { index, level ->
            Text("Sáv ${index + 1}")
            Slider(
                value = level.toFloat(),
                onValueChange = { vm.onEqBandChange(index, it.toInt().toShort()) },
                valueRange = -1500f..1500f
            )
            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(24.dp))

        // BassBoost
        Text("Bass Boost", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = state.bass.toFloat(),
            onValueChange = { vm.onBassChange(it.toInt().toShort()) },
            valueRange = 0f..1000f
        )

        Spacer(Modifier.height(24.dp))

        // Virtualizer
        Text("Virtualizer", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = state.virtualizer.toFloat(),
            onValueChange = { vm.onVirtualizerChange(it.toInt().toShort()) },
            valueRange = 0f..1000f
        )

        Spacer(Modifier.height(24.dp))

        // Loudness
        Text("Loudness Enhancer", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = state.loudness.toFloat(),
            onValueChange = { vm.onLoudnessChange(it.toInt()) },
            valueRange = 0f..1500f
        )
    }
}
