package com.janiplayer.video.player

import androidx.media3.common.MediaItem

object VideoSource {
    fun fromPath(path: String): MediaItem {
        return MediaItem.fromUri(path)
    }
}
