class PlaylistViewModel(
    private val player: Player,
    private val context: Context
) : ViewModel() {

    private val _playlist = MutableStateFlow<List<MediaItem>>(emptyList())
    val playlist = _playlist.asStateFlow()

    fun addFiles(uris: List<Uri>) {
        val items = uris.map { uri ->
            MediaItem.Builder()
                .setUri(uri)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(getTitle(uri))
                        .setArtist(getArtist(uri))
                        .setArtworkUri(getArtwork(uri))
                        .build()
                )
                .build()
        }
        _playlist.value = _playlist.value + items
    }

    fun addDirectory(uris: List<Uri>) {
        addFiles(uris)
    }

    fun playIndex(index: Int) {
        player.setMediaItems(_playlist.value)
        player.seekTo(index, 0)
        player.prepare()
        player.play()
    }

    private fun getTitle(uri: Uri): String =
        uri.lastPathSegment ?: "Ismeretlen"

    private fun getArtist(uri: Uri): String = ""

    private fun getArtwork(uri: Uri): Uri? = null
}
