package com.janiplayer.audio.dsp

class EqPresetCache {

    private val cache = mutableMapOf<Int, ShortArray>()

    fun getOrCreate(bands: Int, generator: () -> ShortArray): ShortArray {
        return cache[bands] ?: generator().also { cache[bands] = it }
    }
}
