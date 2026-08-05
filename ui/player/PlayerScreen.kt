package com.janiplayer.ui.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import coil.compose.rememberAsyncImagePainter
import com.janiplayer.audioeffects.AudioEffectsEngine

@Composable
fun PlayerScreen(
    player: Player,
    engine: AudioEffectsEngine,
    onBack: () -> Unit,
    onOpenPlaylist: () -> Unit
) {
    val currentItem = player.currentMediaItem
    val metadata = currentItem?.mediaMetadata

    val title = metadata?.title?.toString() ?: "Ismeretlen"
    val artist = metadata?.artist?.toString() ?: ""
    val artworkUri = metadata?.artworkUri

    var position by remember { mutableStateOf(0L) }
    var duration by remember { mutableStateOf(player.duration.coerceAtLeast(0L)) }

    // Idő frissítése
    LaunchedEffect(player) {
        while (true) {
            position = player.currentPosition
            duration = player.duration.coerceAtLeast(0L)
            kotlinx.coroutines.delay(200)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jani Player") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(onClick = onOpenPlaylist) {
                        Icon(Icons.Default.QueueMusic, null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Borító
            artworkUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }

            // Cím + előadó
            Column {
                Text(title, style = MaterialTheme.typography.headlineSmall)
                Text(artist, style = MaterialTheme.typography.bodyMedium)
            }

            // Seekbar
            Column {
                Slider(
                    value = position.toFloat(),
                    onValueChange = {
                        player.seekTo(it.toLong())
                    },
                    valueRange = 0f..duration.toFloat()
