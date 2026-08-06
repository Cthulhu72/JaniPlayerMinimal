import com.janiplayer.viewmodel.playlist.PlaylistViewModel

TopAppBar(
    title = { Text("Playlist") },
    navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, null)
        }
    }
)
