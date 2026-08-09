package com.janiplayer.audio.player

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector

object OptimizedAudioPlayer {

    fun create(context: Context): ExoPlayer {
        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(
                buildUponParameters()
                    .setForceHighestSupportedBitrate(false)
                    .setPreferredAudioLanguage("und")
            )
        }

        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                minBufferMs = 5_000,
                maxBufferMs = 30_000,
                bufferForPlaybackMs = 1_500,
                bufferForPlaybackAfterRebufferMs = 3_000
            )
            .build()

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(android.media.AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(android.media.AudioAttributes.USAGE_MEDIA)
            .build()

        return ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .setLoadControl(loadControl)
            .build()
            .apply {
                setAudioAttributes(audioAttributes, true)
                playWhenReady = false
                experimentalSetDynamicSchedulingEnabled(true)
            }
    }

    /**
     * Prepare and start playback. This method sets the media item, prepares the player and
     * sets playWhenReady to true. It does not block; ExoPlayer handles async preparation.
     */
    fun prepareAndPlay(player: ExoPlayer, uri: String) {
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.playWhenReady = true
    }

    fun pause(player: ExoPlayer) {
        player.playWhenReady = false
    }

    fun stop(player: ExoPlayer) {
        player.stop()
        player.clearMediaItems()
    }
}
