import com.janiplayer.viewmodel.player.PlayerViewModel
import com.janiplayer.viewmodel.player.PlayerState
import com.janiplayer.viewmodel.player.PlayerViewModelFactory
import com.janiplayer.viewmodel.playlist.PlaylistViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    player: Player,
    engine: AudioEffectsEngine,
    playlistViewModel: PlaylistViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "player"
    ) {

        // PLAYER
        composable("player") {
            PlayerScreen(
                player = player,
                engine = engine,
                onBack = { /* nincs vissza */ },
                onOpenPlaylist = { navController.navigate("playlist") },
                onOpenSettings = { navController.navigate("settings") }
            )
        }

        // PLAYLIST
        composable("playlist") {
            PlaylistScreen(
                playlist = playlistViewModel.playlist.collectAsState().value,
                currentIndex = player.currentMediaItemIndex,
                onItemClick = { index ->
                    playlistViewModel.playIndex(index)
                    navController.navigate("player")
                },
                onBack = { navController.popBackStack() }
            )
        }

        // SETTINGS
        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onOpenAudioEffects = { navController.navigate("audioeffects") }
            )
        }

        // AUDIO EFFECTS
        composable("audioeffects") {
            AudioEffectsSettingsScreen(
                engine = engine,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
