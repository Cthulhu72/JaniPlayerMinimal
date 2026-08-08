package com.janiplayer.ui.video

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.janiplayer.viewmodel.video.VideoViewModel

@Composable
fun VideoPlayerScreen(viewModel: VideoViewModel) {

    var dragPosition by remember { mutableStateOf<Long?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // VIDEÓ FELÜLET + OVERLAY-EK
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            // Itt lesz majd a videó SurfaceView / PlayerView
            // VideoSurface(player = viewModel.player)

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
        }

        // BUFFER CSÍK + SEEK BAR (Box-ba ágyazva)
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

            // SEEK BAR DRAG LOGIKA
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
