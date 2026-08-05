package com.janiplayerminimal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.janiplayer.ui.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JaniPlayerApp()
        }
    }
}

@Composable
fun JaniPlayerApp() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier) {
        AppNavHost(navController = navController)
    }
}
