package com.janiplayer.ui.player

data class PlayerState(
    val isPlaying: Boolean = false,
    val duration: Long = 0L,
    val position: Long = 0L,
    val title: String = "",
    val artist: String = "",
    val artwork: Any? = null
)
