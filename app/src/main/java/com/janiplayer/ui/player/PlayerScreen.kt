package com.janiplayer.ui.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import com.janiplayer.audioeffects.EffectsState

@Composable
fun PlayerScreen(
    player: ExoPlayer
) {
    val audioSessionId = player.audioSessionId

    val effectsViewModel = remember(audioSessionId) {
        AudioEffectsViewModel(audioSessionId)
    }

    val effectsState by effectsViewModel.state.collectAsState()

    var showEffects by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Saját Player UI-d (borító, cím, vezérlők)
            PlayerControls(player = player)

            // Audio Effects / EQ gomb
            IconButton(
                onClick = { showEffects = !showEffects },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Equalizer,
                    contentDescription = "Audio Effects"
                )
            }

            // Animált AudioEffects panel
            AnimatedVisibility(
                visible = showEffects,
                enter = slideInVertically(
                    initialOffsetY = { it / 2 }
                ) + fadeIn() + scaleIn(initialScale = 0.95f),
                exit = slideOutVertically(
                    targetOffsetY = { it / 2 }
                ) + fadeOut() + scaleOut(targetScale = 0.95f)
            ) {
                Surface(
                    tonalElevation = 6.dp,
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 12.dp)
                ) {
                    AudioEffectsMainScreen(
                        state = effectsState,
                        onBandChange = effectsViewModel::onBandGainChange,
                        onPresetSelected = effectsViewModel::onPresetSelected,
                        onBassChange = effectsViewModel::onBassBoostChange,
                        onVirtualizerChange = effectsViewModel::onVirtualizerChange,
                        onLoudnessChange = effectsViewModel::onLoudnessChange
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerControls(
    player: ExoPlayer
) {
    // Itt marad a saját Jani Player vezérlő UI-d:
    // - borítókép
    // - cím, előadó
    // - play/pause
    // - seekbar
    // - stb.
}
