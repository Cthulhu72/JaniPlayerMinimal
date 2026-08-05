package com.janiplayer.audioeffects.data

data class DspConfig(
    val eqBands: List<Short> = listOf(0, 0, 0, 0, 0),
    val bass: Short = 0,
    val virtualizer: Short = 0,
    val loudness: Int = 0
)
