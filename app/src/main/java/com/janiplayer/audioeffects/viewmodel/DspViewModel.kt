fun onPresetSelected(preset: EqPreset) {
    // 1) Engine-re írjuk
    preset.bands.forEachIndexed { index, level ->
        engine.setEqBand(index.toShort(), level)
    }

    // 2) DataStore-ba mentjük
    repository.updateEqBands(preset.bands)

    // 3) UI state frissítése
    _state.value = _state.value.copy(eqBands = preset.bands)
}
