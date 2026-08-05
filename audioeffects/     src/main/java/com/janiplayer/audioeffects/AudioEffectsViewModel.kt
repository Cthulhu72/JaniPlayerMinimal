package com.janiplayer.audioeffects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AudioEffectsViewModel(
    private val audioSessionId: Int
) : ViewModel() {

    private val engine = AudioEffectsEngine(audioSessionId)

    val state: StateFlow<EffectsState>
        get() = engine.state

    init {
        engine.init()
    }

    fun onBandGainChange(bandIndex: Int, gain: Float) {
        viewModelScope.launch {
            engine.setBandGain(bandIndex, gain)
        }
    }

    fun onPresetSelected(preset: String) {
        viewModelScope.launch {
            engine.selectPreset(preset)
        }
    }

    fun onBassBoostChange(value: Float) {
        viewModelScope.launch {
            engine.setBassBoost(value)
        }
    }

    fun onVirtualizerChange(value: Float) {
        viewModelScope.launch {
            engine.setVirtualizer(value)
        }
    }

    fun onLoudnessChange(value: Float) {
        viewModelScope.launch {
            engine.setLoudness(value)
        }
    }

    override fun onCleared() {
        super.onCleared()
        engine.release()
    }
}
