package com.janiplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.MediaItem
import com.janiplayer.ui.navigation.AppNavHost

class MainActivity : ComponentActivity() {

    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Media3 ExoPlayer inicializálás
        player = ExoPlayer.Builder(this).build().apply {
            val mediaItem = MediaItem.fromUri("asset:///sample.mp3")
            setMediaItem(mediaItem)
            prepare()
        }

        setContent {
            JaniPlayerApp(player)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}

@Composable
fun JaniPlayerApp(player: ExoPlayer) {
    val navController = rememberNavController()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
    ) {
        AppNavHost(
            navController = navController,
            player = player
        )
    }
}
