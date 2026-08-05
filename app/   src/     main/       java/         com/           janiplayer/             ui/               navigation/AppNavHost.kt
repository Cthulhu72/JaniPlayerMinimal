package com.janiplayer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.janiplayer.ui.player.PlayerScreen
import com.janiplayer.ui.settings.SettingsScreen
import com.janiplayer.ui.settings.AudioEffectsSettingsScreen
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun AppNavHost(
    navController: NavHostController,
    player: ExoPlayer
) {
    NavHost(
        navController = navController,
        startDestination = "player"
    ) {
        composable("player") {
            PlayerScreen(player = player)
        }

        composable("settings") {
            SettingsScreen(
                onAudioEffectsClick = { navController.navigate("audio_effects_settings") },
                onThemeClick = { /* később: navController.navigate("theme_settings") */ },
                onAppInfoClick = { /* később: navController.navigate("app_info") */ }
            )
        }

        composable("audio_effects_settings") {
            AudioEffectsSettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
