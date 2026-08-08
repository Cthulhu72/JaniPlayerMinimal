package com.janiplayer.audio.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.janiplayer.audio.player.OptimizedAudioPlayer
import com.janiplayer.audio.dsp.AudioEffects
import com.janiplayer.audio.viewmodel.AudioViewModel

@Composable
fun AudioPlayerScreen(
    context: Context,
    path: String
) {
    val player = remember { OptimizedAudioPlayer.create(context) }
    val viewModel = remember { AudioViewModel(player) }

    val effects = remember {
        AudioEffects(player.audioSessionId)
    }

    DisposableEffect(Unit) {
        onDispose {
            effects.release()
            player.release()
        }
    }

    LaunchedEffect(path) {
        player.setMediaItem(androidx.media3.common.MediaItem.fromUri(path))
        player.prepare()
        player.play()
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Lejátszás: $path")
        Text("Pozíció: ${viewModel.position / 1000}s")
        Text("Állapot: ${if (viewModel.isPlaying) "Lejátszik" else "Szünet"}")
    }
}
