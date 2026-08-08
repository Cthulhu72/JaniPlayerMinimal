// IDE KELL BEÍRNI – VideoPlayerScreen.kt
// A meglévő Slider helyére EZT kell tenni

package com.janiplayer.ui.video

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.janiplayer.viewmodel.video.VideoViewModel

@Composable
fun VideoPlayerScreen(viewModel: VideoViewModel) {

    var dragPosition by remember { mutableStateOf<Long?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
) {
    // IDE KELL BEÍRNI – VideoPlayerScreen.kt
// A videót tartalmazó Box BELSEJÉBE

if (viewModel.isBuffering) {
    CircularProgressIndicator(
        modifier = Modifier
            .align(Alignment.Center)
            .size(48.dp)
    )
}
            // Itt lesz majd a videó SurfaceView / PlayerView
}
        // Itt lesz majd a videó SurfaceView / PlayerView
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // VideoSurface(player = viewModel.player)
        }

        // SEEK BAR DRAG LOGIKA – IDE KELL TENNI
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
