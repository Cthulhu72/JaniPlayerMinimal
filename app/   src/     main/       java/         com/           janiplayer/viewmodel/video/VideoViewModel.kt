package com.janiplayer.viewmodel.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.common.PlaybackException
import androidx.media3.common.Timeline
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class VideoViewModel(
    val player: Player
) : ViewModel() {

    var currentPosition by mutableStateOf(0L)
    var bufferedPosition by mutableStateOf(0L)
    var duration by mutableStateOf(0L)

    var isPlaying by mutableStateOf(false)
    var isBuffering by mutableStateOf(false)
    var isError by mutableStateOf(false)

    var controlsVisible by mutableStateOf(true)
    var isFullscreen by mutableStateOf(false)

    init {
        // PLAYER LISTENER
        player.addListener(object : Player.Listener {

            override fun onPlaybackStateChanged(state: Int) {
                isBuffering = state == Player.STATE_BUFFERING
                isError = state == Player.STATE_IDLE || state == Player.STATE_ENDED
            }

            override fun onIsPlayingChanged(isPlayingNow: Boolean) {
                isPlaying = isPlayingNow
            }

            override fun onTimelineChanged(timeline: Timeline, reason: Int) {
                duration = player.duration
            }

            override fun onPlayerError(error: PlaybackException) {
                isError = true
            }
        })

        // IDŐZÍTETT FRISSÍTÉS (currentPosition + buffer)
        viewModelScope.launch {
            while (true) {
                currentPosition = player.currentPosition
                bufferedPosition = player.bufferedPosition
                duration = player.duration
                delay(100L)
            }
        }

        // CONTROLS AUTO-HIDE
        viewModelScope.launch {
            while (true) {
                if (controlsVisible) {
                    delay(3000)
                    controlsVisible = false
                }
                delay(50)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

    fun toggleControls() {
        controlsVisible = !controlsVisible
    }

    fun toggleFullscreen() {
        isFullscreen = !isFullscreen
    }
}
