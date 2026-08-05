suspend fun updateEqBands(bands: List<Short>) {
    dataStore.updateData { config ->
        config.copy(eqBands = bands)
    }
}
