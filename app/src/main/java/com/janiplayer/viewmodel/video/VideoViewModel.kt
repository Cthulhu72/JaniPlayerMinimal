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
                    
         .pointerInput(Unit) {
             detectVerticalDragGestures(
                 onVerticalDrag = { change, dragAmount ->
                    if (change.position.x < size.width / 2f) {
                // BAL OLDAL → fényerő
                       viewModel.adjustBrightness(dragAmount)
                    } else {
                // JOBB OLDAL → hangerő
                       viewModel.adjustVolume(dragAmount)
            }
        }
    )
                    }
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
// CONTROLS OVERLAY
if (viewModel.controlsVisible) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)
            .padding(24.dp)
    ) {

        // Play / Pause gomb
        IconButton(
            onClick = {
                if (viewModel.player.isPlaying) {
                    viewModel.player.pause()
                } else {
                    viewModel.player.play()
                }
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = if (viewModel.player.isPlaying)
                    Icons.Default.Pause
                else
                    Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White
            )
        }

        // Bal oldali 10s vissza
        IconButton(
            onClick = { viewModel.player.seekBack() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.Replay10,
                contentDescription = null,
                tint = Color.White
            )
        }

        // Jobb oldali 10s előre
        IconButton(
            onClick = { viewModel.player.seekForward() },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Forward10,
                contentDescription = null,
                tint = Color.White
            )
        }

        // Idő kijelzés (current / total)
        Text(
            text = "${viewModel.formatTime(viewModel.currentPosition)} / ${viewModel.formatTime(viewModel.duration)}",
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
        )

        // Fullscreen gomb
        IconButton(
            onClick = { viewModel.toggleFullscreen() },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = if (viewModel.isFullscreen)
                    Icons.Default.FullscreenExit
                else
                    Icons.Default.Fullscreen,
                contentDescription = null,
                tint = Color.White
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
    fun toggleControls() {
    controlsVisible = !controlsVisible
    }
    
    var isFullscreen by mutableStateOf(false)
    
    fun toggleFullscreen() {
        isFullscreen = !isFullscreen
        
    fun adjustVolume(amount: Float) {
    // TODO: Media3 VolumeControl vagy AudioManager
}
    fun adjustBrightness(amount: Float) {
    // TODO: Window attributes
}
    
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
