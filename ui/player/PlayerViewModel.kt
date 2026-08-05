package com.janiplayer.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val player: ExoPlayer
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val state: StateFlow<PlayerState> = _state

    init {
        observePlayer()
        startPositionUpdater()
    }

    private fun observePlayer() {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updateState()
            }

            override fun onMediaMetadataChanged(mediaMetadata: androidx.media3.common.MediaMetadata) {
                updateState()
            }
        })
    }

    private fun startPositionUpdater() {
        viewModelScope.launch {
            while (true) {
                updateState()
                delay(200L)
            }
        }
    }

    private fun updateState() {
        val metadata = player.mediaMetadata

        _state.value = PlayerState(
            isPlaying = player.isPlaying,
            duration = player.duration.coerceAtLeast(0L),
            position = player.currentPosition.coerceAtLeast(0L),
            title = metadata.title?.toString() ?: "",
            artist = metadata.artist?.toString() ?: "",
            artwork = metadata.artworkData
        )
    }

    fun playPause() {
        if (player.isPlaying) player.pause()
        else player.play()
    }

    fun seekTo(position: Long) {
        player.seekTo(position)
    }
}
