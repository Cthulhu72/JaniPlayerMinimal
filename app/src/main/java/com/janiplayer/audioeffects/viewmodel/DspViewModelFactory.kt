package com.janiplayer.audioeffects.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janiplayer.audioeffects.AudioEffectsEngine
import com.janiplayer.audioeffects.data.DspRepository

class DspViewModelFactory(
    private val engine: AudioEffectsEngine,
    private val repository: DspRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DspViewModel(
            engine = engine,
            repository = repository
        ) as T
    }
}
