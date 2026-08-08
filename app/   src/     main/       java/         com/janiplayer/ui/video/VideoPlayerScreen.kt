package com.janiplayer.ui.video

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.detectTapGestures
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.compose.ui.platform.LocalContext
import com.janiplayer.viewmodel.video.VideoViewModel
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayerScreen(viewModel: VideoViewModel) {

    var dragPosition by remember { mutableStateOf<Long?>(null) }

    val activity = LocalContext.current as Activity
    val window = activity.window

    // FULLSCREEN SYSTEM UI KEZELÉS
    LaunchedEffect(viewModel.isFullscreen) {
        if (viewModel.isFullscreen) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                hide(WindowInsetsCompat.Type.systemBars())
                systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            WindowCompat.setDecorFitsSystemWindows(window, true)
            WindowInsetsControllerCompat(window, window.decorView).show(
                WindowInsetsCompat.Type.systemBars()
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // VIDEÓ FELÜLET + OVERLAY-EK + CONTROLS
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { offset ->
                            val width = size.width
                            if (offset.x < width / 2f) {
                                viewModel.player.seekBack()
                            } else {
                                viewModel.player.seekForward()
                            }
                        },
                        onTap = { viewModel.toggleControls() }
                    )
                }
        ) {

            // VIDEO SURFACE
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    PlayerView(context).apply {
                        player = viewModel.player
                        useController = false
                    }
                },
                update = { view ->
                    view.player = viewModel.player
                }
            )

            // BUFFERING OVERLAY
            if (viewModel.isBuffering) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp)
                )
            }

            // ERROR OVERLAY
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

            // CONTROLS OVERLAY
            if (viewModel.controlsVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .padding(24.dp)
                ) {

                    // Play / Pause
                    IconButton(
                        onClick = {
                            if (viewModel.player.isPlaying) viewModel.player.pause()
                            else viewModel.player.play()
                        },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Icon(
                            imageVector = if (viewModel.player.isPlaying)
                                Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    // 10s vissza
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

                    // 10s előre
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

                    // Idő kijelzés
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
                                Icons.Default.FullscreenExit else Icons.Default.Fullscreen,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }

        // BUFFER CSÍK + SEEK BAR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            // BUFFER CSÍK
            LinearProgressIndicator(
                progress = if (viewModel.duration > 0)
                    viewModel.bufferedPosition / viewModel.duration.toFloat()
                else 0f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .alpha(0.3f)
            )

            // SEEK BAR
            Slider(
                value = (dragPosition ?: viewModel.currentPosition).toFloat(),
                onValueChange = { newValue ->
                    dragPosition = newValue.toLong()
                },
                onValueChangeFinished = {
                    dragPosition?.let { finalPos ->
                        viewModel.player.seekTo(finalPos)
                    }
                    dragPosition = null
                },
                valueRange = 0f..viewModel.duration.toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
