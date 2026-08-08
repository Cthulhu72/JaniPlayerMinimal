package com.janiplayer.audio.dsp

import android.media.audiofx.*

class AudioEffects(
    sessionId: Int
) {
    val equalizer = Equalizer(0, sessionId).apply {
        enabled = true
    }

    val bassBoost = BassBoost(0, sessionId).apply {
        enabled = true
        setStrength(500)
    }

    val virtualizer = Virtualizer(0, sessionId).apply {
        enabled = true
        setStrength(500)
    }

    val loudness = LoudnessEnhancer(sessionId).apply {
        setTargetGain(500)
    }

    fun release() {
        equalizer.release()
        bassBoost.release()
        virtualizer.release()
        loudness.release()
    }
}
