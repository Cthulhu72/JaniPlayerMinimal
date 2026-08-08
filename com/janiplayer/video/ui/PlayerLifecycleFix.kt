package com.janiplayer.video.ui

import androidx.compose.runtime.DisposableEffect
import androidx.media3.common.Player

@Composable
fun PlayerLifecycleFix(player: Player) {
    DisposableEffect(Unit) {
        onDispose {
            player.clearVideoSurface()
        }
    }
}
