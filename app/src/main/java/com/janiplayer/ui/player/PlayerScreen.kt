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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.janiplayer.audioeffects.AudioEffectsDataStore
import com.janiplayer.audioeffects.AudioEffectsMainScreen
import com.janiplayer.audioeffects.AudioEffectsViewModel

@Composable
fun PlayerScreen(
    player: ExoPlayer
) {
    val audioSessionId = player.audioSessionId
    val context = LocalContext.current

    // DataStore példány
    val dataStore = remember { AudioEffectsDataStore(context) }

    // ViewModel példány
    val effectsViewModel = remember(audioSessionId) {
        AudioEffectsViewModel(audioSessionId, dataStore)
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
