package com.janiplayer.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.janiplayer.audioeffects.AudioEffectsDataStore
import com.janiplayer.audioeffects.AudioEffectsViewModel
import com.janiplayer.audioeffects.AudioEffectsMainScreen

@Composable
fun AudioEffectsSettingsScreen(
    onBack: () -> Unit = {}
) {
    val vm: DspViewModel = viewModel(
    factory = DspViewModelFactory(
        engine = engine,
        repository = DspRepository(context)
    )
)
    val context = LocalContext.current
    val dataStore = remember { AudioEffectsDataStore(context) }

    // Mivel itt nincs player, audioSessionId = 0 → csak a UI + DataStore működik
    val viewModel = remember {
        AudioEffectsViewModel(audioSessionId = 0, dataStore = dataStore)
    }

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Audio Effects") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            AudioEffectsMainScreen(
                state = state,
                accentColor = MaterialTheme.colorScheme.primary,
                onBandChange = viewModel::onBandGainChange,
                onPresetSelected = viewModel::onPresetSelected,
                onBassChange = viewModel::onBassBoostChange,
                onVirtualizerChange = viewModel::onVirtualizerChange,
                onLoudnessChange = viewModel::onLoudnessChange
            )
        }
    }
}
