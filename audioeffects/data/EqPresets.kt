package com.janiplayer.audioeffects.data

object EqPresets {

    val Rock = EqPreset(
        name = "Rock",
        bands = listOf(300, 150, 0, 150, 300).map { it.toShort() }
    )

    val Pop = EqPreset(
        name = "Pop",
        bands = listOf(200, 100, 0, 100, 200).map { it.toShort() }
    )

    val Jazz = EqPreset(
        name = "Jazz",
        bands = listOf(150, 100, 0, 100, 150).map { it.toShort() }
    )

    val Classical = EqPreset(
        name = "Classical",
        bands = listOf(100, 50, 0, 50, 100).map { it.toShort() }
    )

    val Flat = EqPreset(
        name = "Flat",
        bands = listOf(0, 0, 0, 0, 0).map { it.toShort() }
    )

    val All = listOf(Rock, Pop, Jazz, Classical, Flat)
}
