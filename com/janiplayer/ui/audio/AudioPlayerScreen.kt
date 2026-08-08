Box(
    Modifier
        .fillMaxWidth()
        .background(Color.Black.copy(alpha = 0.3f))
        .padding(16.dp)
) {
    Text(
        text = currentSong.title,
        style = MaterialTheme.typography.titleLarge,
        color = Color.White
    )
}
