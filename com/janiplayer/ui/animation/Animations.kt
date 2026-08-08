package com.janiplayer.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

@OptIn(ExperimentalAnimationApi::class)
fun fadeInFast() = fadeIn(animationSpec = tween(150))

@OptIn(ExperimentalAnimationApi::class)
fun fadeOutFast() = fadeOut(animationSpec = tween(150))

@OptIn(ExperimentalAnimationApi::class)
fun slideInRight() = slideInHorizontally { it }

@OptIn(ExperimentalAnimationApi::class)
fun slideOutLeft() = slideOutHorizontally { -it }
