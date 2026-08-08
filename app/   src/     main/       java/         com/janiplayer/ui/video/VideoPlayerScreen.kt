// IDE KELL BEÍRNI – VideoPlayerScreen.kt
// A meglévő Slider helyére EZT kell tenni

var dragPosition by remember { mutableStateOf<Long?>(null) }

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
