package com.janiplayer.ui.player

import com.janiplayer.viewmodel.playlist.PlaylistViewModel
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class PlaylistViewModel(
    private val context: Context,
    private val player: ExoPlayer
) : ViewModel() {

    private val items = mutableListOf<MediaItem>()

    fun addFile(uri: Uri) {
        val item = MediaItem.fromUri(uri)
        items.add(item)
        player.addMediaItem(item)
        player.prepare()
    }

    fun addFiles(uris: List<Uri>) {
        uris.forEach { addFile(it) }
    }

    fun addDirectory(uris: List<Uri>) {
        uris.forEach { addFile(it) }
    }

    fun playIndex(index: Int) {
        player.seekToDefaultPosition(index)
        player.play()
    }

    fun next() {
        player.seekToNext()
    }

    fun previous() {
        player.seekToPrevious()
    }

    fun getPlaylist(): List<MediaItem> = items
}
