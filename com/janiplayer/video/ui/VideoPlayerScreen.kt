package com.janiplayer.video.ui

import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.janiplayer.video.player.OptimizedPlayer
import com.janiplayer.video.viewmodel.PositionState

@Composable
fun VideoPlayerScreen(context: android.content.Context) {

    val player = remember { OptimizedPlayer.create(context) }
    val positionState = remember { PositionState(player) }

    PlayerLifecycleFix(player)

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
