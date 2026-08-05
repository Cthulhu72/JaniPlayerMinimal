package com.janiplayer.ui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.janiplayer.audioeffects.AudioEffectsMainScreen
import com.janiplayer.audioeffects.AudioEffectsViewModel

@Composable
fun PlayerScreen(
    player: ExoPlayer
) {
    val audioSessionId = player.audioSessionId

    val effectsViewModel = remember(audioSessionId) {
        AudioEffectsViewModel(audioSessionId)
    }

    val effectsState by effects
