import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*

composable("playlist") {
    val vm: PlaylistViewModel = viewModel(factory = PlaylistViewModelFactory(player, context))

    PlaylistScreen(
        playlist = vm.playlist.collectAsState().value,
        currentIndex = player.currentMediaItemIndex,
        onItemClick = { index ->
            vm.playIndex(index)
            navController.navigate("player")
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    player: ExoPlayer
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = "player",
        enterTransition = { slideInHorizontally { it } + fadeIn() },
        exitTransition = { slideOutHorizontally { -it } + fadeOut() },
        popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
        popExitTransition = { slideOutHorizontally { it } + fadeOut() }
    ) {
        composable("player") {
            PlayerScreen(player = player)
        }

        composable("settings") {
            SettingsScreen(
                onAudioEffectsClick = { navController.navigate("audio_effects_settings") },
                onThemeClick = {},
                onAppInfoClick = {}
            )
        }

        composable("audio_effects_settings") {
            AudioEffectsSettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
