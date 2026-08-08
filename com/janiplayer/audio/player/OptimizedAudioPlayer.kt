package com.janiplayer.audio.player

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.DefaultLoadControl

object OptimizedAudioPlayer {

    fun create(context: Context): ExoPlayer {

        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                minBufferMs = 5000,
                maxBufferMs = 15000,
                bufferForPlaybackMs = 1000,
                bufferForPlaybackAfterRebufferMs = 2000
            )
            .build()

        return ExoPlayer.Builder(context)
            .setLoadControl(loadControl)
            .experimentalSetDynamicSchedulingEnabled(true)
            .build()
    }
}
