package com.janiplayer.video.player

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.exoplayer.DefaultLoadControl

object OptimizedPlayer {

    fun create(context: Context): ExoPlayer {

        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(
                buildUponParameters()
                    .setForceHighestSupportedBitrate(false)
                    .setMaxVideoBitrate(5_000_000)
                    .setAllowVideoNonSeamlessAdaptiveness(true)
            )
        }

        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                minBufferMs = 15000,
                maxBufferMs = 50000,
                bufferForPlaybackMs = 2500,
                bufferForPlaybackAfterRebufferMs = 5000
            )
            .build()

        return ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .setLoadControl(loadControl)
            .experimentalSetDynamicSchedulingEnabled(true)
            .build()
    }
}
