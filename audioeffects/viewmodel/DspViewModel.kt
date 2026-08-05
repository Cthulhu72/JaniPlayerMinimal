class DspViewModel(
    private val engine: AudioEffectsEngine,
    private val repository: DspRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DspConfig())
    val state: StateFlow<DspConfig> = _state

    init {
        viewModelScope.launch {
            repository.configFlow.collect { config ->
                _state.value = config

                // Apply DSP to engine
                config.eqBands.forEachIndexed { index, level ->
                    engine.setEqBand(index.toShort(), level)
                }
                engine.setBass(config.bass)
                engine.setVirtualizer(config.virtualizer)
                engine.setLoudness(config.loudness)
            }
        }
    }

    fun onEqBandChange(index: Int, level: Short) {
        viewModelScope.launch {
            repository.updateEqBands(
                _state.value.eqBands.toMutableList().apply { this[index] = level }
            )
        }
    }

    fun onBassChange(value: Short) {
        viewModelScope.launch { repository.updateBass(value) }
    }

    fun onVirtualizerChange(value: Short) {
        viewModelScope.launch { repository.updateVirtualizer(value) }
    }

    fun onLoudnessChange(value: Int) {
        viewModelScope.launch { repository.updateLoudness(value) }
    }
}
