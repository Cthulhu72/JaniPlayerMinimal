package com.janiplayer.ui.performance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun <T> stable(value: T): T = remember { value }
