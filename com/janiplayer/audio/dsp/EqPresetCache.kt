package com.janiplayer.audio.dsp

/**
 * Egyszerű EQ preset cache: néhány előre definiált preset,
 * gyors beállításhoz a UI-ból.
 */
object EqPresetCache {

    data class Preset(val name: String, val bandLevels: ShortArray)

    private val presets = listOf(
        Preset("Flat", shortArrayOf(0, 0, 0, 0, 0)),
        Preset("Bass Boost", shortArrayOf(400, 200, 0, -100, -200)),
        Preset("Vocal", shortArrayOf(-100, 0, 300, 200, -100)),
        Preset("Treble", shortArrayOf(-200, -100, 0, 300, 500))
    )

    fun getAll(): List<Preset> = presets

    fun findByName(name: String): Preset? = presets.find { it.name == name }
}
