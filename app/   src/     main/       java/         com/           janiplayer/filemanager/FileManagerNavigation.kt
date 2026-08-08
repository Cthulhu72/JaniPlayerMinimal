composable("filemanager") {
    FileManagerScreen(
        viewModel = hiltViewModel(),
        onAddToPlaylist = { playlistId ->
            viewModel.addSelectedToPlaylist(playlistId)
        }
    )
}
