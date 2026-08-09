package com.janiplayer.audio.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.janiplayer.audio.viewmodel.AudioViewModel
import com.janiplayer.audio.dsp.EqPresetCache
import kotlinx.coroutines.launch

/**
 * Ultra‑tuning UI:
 * - derivedStateOf a pozícióhoz, hogy minimalizáljuk a redraw‑t
 * - debounce a slider eseményeknél
 * - csak a szükséges komponensek redrawolnak
 */

@Composable
fun AudioPlayerScreen(
    context: Context,
    uri: String,
    vm: AudioViewModel = viewModel()
) {
    // init player lazily
    LaunchedEffect(Unit) {
        vm.initPlayer(context)
    }

    // ultra‑tuning: derived state for position to avoid recomposition storms
    val uiState by vm.uiState.collectAsState()
    val positionDerived by remember(uiState.positionMs) {
        derivedStateOf { uiState.positionMs }
    }

    val coroutineScope = rememberCoroutineScope()
    var localVolume by remember { mutableStateOf(uiState.volume) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(text = "Jani Ultra Audio", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { vm.playUri(uri) }) {
                Text("Play")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { vm.pause() }) {
                Text("Pause")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { vm.stop() }) {
                Text("Stop")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Position display — derivedStateOf ensures this text updates only when position changes
        Text("Pozíció: ${positionDerived / 1000}s / ${uiState.durationMs / 1000}s")

        Spacer(modifier = Modifier.height(12.dp))

        // Volume slider with debounce (simple coroutine debounce)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Volume", modifier = Modifier.width(72.dp))
            Slider(
                value = localVolume,
                onValueChange = { v -> localVolume = v },
                onValueChangeFinished = {
                    coroutineScope.launch {
                        vm.setVolume(localVolume)
                    }
                },
                valueRange = 0f..1f,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // EQ presets
        Text("EQ Presets", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            EqPresetCache.getAll().forEach { preset ->
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clickable {
                            vm.applyEqPreset(preset.bandLevels)
                        },
                    color = Color(0xFFEEEEEE),
                    tonalElevation = 2.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(preset.name)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Simple DSP controls
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { vm.setBass(1000) }) { Text("Bass+") }
            Button(onClick = { vm.setBass(0) }) { Text("Bass-") }
            Button(onClick = { vm.setVirtualizer(1000) }) { Text("Virt+") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Minimal visualizer placeholder (kevesebb redraw)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color(0xFF111111))
        ) {
            // intentionally minimal: avoid frequent recomposition
            Text(
                text = if (uiState.isPlaying) "Playing" else "Stopped",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
