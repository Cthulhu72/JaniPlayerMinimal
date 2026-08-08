package com.janiplayer.video.ui

import android.content.Context
import androidx.media3.ui.PlayerView
import androidx.media3.common.Player

fun createOptimizedPlayerView(context: Context, player: Player): PlayerView {
    return PlayerView(context).apply {
        useController = false
        setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
        setSurfaceType(PlayerView.SURFACE_TYPE_SURFACE_VIEW)
        this.player = player
    }
}
