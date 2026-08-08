package com.janiplayer.video.ui

import android.app.Activity
import androidx.core.view.WindowCompat

object FullscreenController {

    fun apply(activity: Activity, fullscreen: Boolean) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, !fullscreen)
    }
}
