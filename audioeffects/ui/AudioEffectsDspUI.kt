@Composable
fun AudioEffectsDspUI(vm: DspViewModel) {
    val state by vm.state.collectAsState()

    Column {

        Text("Equalizer", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        state.eqBands.forEachIndexed { index, level ->
            Text("Sáv ${index + 1}")
            Slider(
                value = level.toFloat(),
                onValueChange = { vm.onEqBandChange(index, it.toInt().toShort()) },
                valueRange = -1500f..1500f
            )
            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(24.dp))

        Text("Bass Boost", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = state.bass.toFloat(),
            onValueChange = { vm.onBassChange(it.toInt().toShort()) },
            valueRange = 0f..1000f
        )

        Spacer(Modifier.height(24.dp))

        Text("Virtualizer", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = state.virtualizer.toFloat(),
            onValueChange = { vm.onVirtualizerChange(it.toInt().toShort()) },
            valueRange = 0f..1000f
        )

        Spacer(Modifier.height(24.dp))

        Text("Loudness Enhancer", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = state.loudness.toFloat(),
            onValueChange = { vm.onLoudnessChange(it.toInt()) },
            valueRange = 0f..1500f
        )
    }
}
