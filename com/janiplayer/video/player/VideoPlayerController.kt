package com.janiplayer.video.player

import androidx.media3.common.Player

class VideoPlayerController(private val player: Player) {

    fun play() = player.play()
    fun pause() = player.pause()
    fun seekTo(ms: Long) = player.seekTo(ms)
}
