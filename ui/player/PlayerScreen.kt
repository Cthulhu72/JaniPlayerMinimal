package com.janiplayer.ui.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun PlayerScreen(player: ExoPlayer) {

    val vm: PlayerViewModel = viewModel(factory = PlayerViewModelFactory(player))
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Borító
        state.artwork?.let { bytes ->
            val bitmap = androidx.compose.ui.graphics.ImageBitmap.imageFromByteArray(bytes)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Cím + előadó
        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = state.artist,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Idő kijelzés
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(formatTime(state.position))
            Text(formatTime(state.duration))
        }

        // SeekBar
        Slider(
            value = state.position.toFloat(),
            onValueChange = { vm.seekTo(it.toLong()) },
            valueRange = 0f..state.duration.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Play/Pause
        Button(
            onClick = { vm.playPause() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isPlaying) "Pause" else "Play")
        }
    }
}

private fun formatTime(ms: Long): String {
    val totalSec = ms / 1000
    val min = totalSec / 60
    val sec = totalSec % 60
    return "%02d:%02d".format(min, sec)
}
