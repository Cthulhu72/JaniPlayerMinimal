package com.janiplayer.audio.dsp

import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.media.audiofx.Virtualizer
import android.util.Log

/**
 * Egyszerű wrapper az Android audiofx osztályok köré.
 * Tervezve: létrehozás audioSessionId alapján, release() hívható.
 */
class AudioEffects(private val audioSessionId: Int) {

    private var equalizer: Equalizer? = null
    private var bassBoost: BassBoost? = null
    private var virtualizer: Virtualizer? = null

    init {
        try {
            equalizer = Equalizer(0, audioSessionId).apply {
                enabled = true
            }
            bassBoost = BassBoost(0, audioSessionId).apply {
                enabled = true
            }
            virtualizer = Virtualizer(0, audioSessionId).apply {
                enabled = true
            }
        } catch (t: Throwable) {
            Log.w("AudioEffects", "AudioFX init failed: ${t.message}")
            release()
        }
    }

    fun setEqBandLevel(band: Short, level: Short) {
        equalizer?.let {
            if (band < it.numberOfBands) {
                it.setBandLevel(band, level)
            }
        }
    }

    fun setBassStrength(strength: Short) {
        bassBoost?.setStrength(strength)
    }

    fun setVirtualizerStrength(strength: Short) {
        virtualizer?.setStrength(strength)
    }

    fun release() {
        try {
            equalizer?.release()
            bassBoost?.release()
            virtualizer?.release()
        } catch (_: Throwable) { }
        equalizer = null
        bassBoost = null
        virtualizer = null
    }
}
