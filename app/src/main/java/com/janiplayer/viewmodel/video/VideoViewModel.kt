package com.janiplayer.viewmodel.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

Box(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = { offset ->
                    val width = size.width
                    if (offset.x < width / 2f) {
                        // BAL OLDAL → vissza 10 másodpercet
                        viewModel.player.seekBack()
                    } else {
                        // JOBB OLDAL → előre 10 másodpercet
                        viewModel.player.seekForward()
                    }
                },
                onTap = {
                    viewModel.toggleControls()
                }
            )
        }
) {
    // VideoSurface(player = viewModel.player)

    if (viewModel.isBuffering) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
        )
    }

    if (viewModel.isError) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = "Hiba történt a videó lejátszásában",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

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
    
    fun toggleFullscreen() {
        isFullscreen = !isFullscreen
    
    init {
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
        })

        viewModelScope.launch {
            while (true) {
                currentPosition = player.currentPosition
                bufferedPosition = player.bufferedPosition
                delay(50)
            }
        }
    }

        var controlsVisible by mutableStateO
        
        fun toggleControls() {
        controlsVisible = !controlsVisible
            if (viewModel.controlsVisible) {
    // Play/pause, fullscreen, idő kijelzés stb.
            }
    }

    fun toggleFullscreen() {
        isFullscreen = !isFullscreen
    }
}
