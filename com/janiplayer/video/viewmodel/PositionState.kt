package com.janiplayer.video.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PositionState(player: Player) : ViewModel() {

    var rawPosition by mutableStateOf(0L)

    val position by derivedStateOf { rawPosition }

    init {
        viewModelScope.launch {
            while (true) {
                rawPosition = player.currentPosition
                delay(100)
            }
        }
    }
}
