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

@Composable
fun AudioPlayerScreen(
    context: Context,
    uri: String,
    vm: AudioViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        vm.initPlayer(context)
    }

    val uiState by vm.uiState.collectAsState()
    val positionDerived by remember { derivedStateOf { uiState.positionMs } }

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

        Text("Pozíció: ${positionDerived / 1000}s / ${uiState.durationMs / 1000}s")

        Spacer(modifier = Modifier.height(12.dp))

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

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { vm.setBass(1000) }) { Text("Bass+") }
            Button(onClick = { vm.setBass(0) }) { Text("Bass-") }
            Button(onClick = { vm.setVirtualizer(1000) }) { Text("Virt+") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color(0xFF111111))
        ) {
            Text(
                text = if (uiState.isPlaying) "Playing" else "Stopped",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
