package com.janiplayer.audio.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AudioViewModel(
    private val player: Player
) : ViewModel() {

    var rawPosition by mutableStateOf(0L)
    val position by derivedStateOf { rawPosition }

    var isPlaying by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            while (true) {
                rawPosition = player.currentPosition
                isPlaying = player.isPlaying
                delay(100)
            }
        }
    }
}
