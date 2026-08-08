val alpha = optimizedFade(viewModel.isPlaying)

OptimizedOverlay(alpha) {
    Text("Pozíció: ${viewModel.position / 1000}s")
}
