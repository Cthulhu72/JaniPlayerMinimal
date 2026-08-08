package com.janiplayer.video.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.janiplayer.video.player.OptimizedPlayer
import com.janiplayer.video.player.VideoSource
import com.janiplayer.video.player.VideoPlayerController
import com.janiplayer.video.viewmodel.PositionState

@Composable
fun VideoPlayerScreen(
    context: Context,
    path: String
) {
    val player = remember { OptimizedPlayer.create(context) }
    val controller = remember { VideoPlayerController(player) }
    val positionState = remember { PositionState(player) }

    PlayerLifecycleFix(player)

    LaunchedEffect(path) {
        player.setMediaItem(VideoSource.fromPath(path))
        player.prepare()
        controller.play()
    }

    Column(Modifier.fillMaxSize()) {

        AndroidView(
            factory = {
                createOptimizedPlayerView(context, player)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        VideoOverlay(controlsVisible = true) {
            Text("Pozíció: ${positionState.position / 1000}s")
        }
    }
}
