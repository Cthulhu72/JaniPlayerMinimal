package com.janiplayer.audioeffects.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dspDataStore: DataStore<DspPreferences> by dataStore(
    fileName = "dsp_prefs.pb",
    serializer = DspPreferencesSerializer
)

class DspRepository(private val context: Context) {

    val configFlow: Flow<DspConfig> = context.dspDataStore.data.map { prefs ->
        DspConfig(
            eqBands = prefs.eqBands.map { it.toShort() },
            bass = prefs.bass.toShort(),
            virtualizer = prefs.virtualizer.toShort(),
            loudness = prefs.loudness
        )
    }

    suspend fun updateEqBands(bands: List<Short>) {
        context.dspDataStore.updateData { prefs ->
            prefs.toBuilder()
                .clearEqBands()
                .addAllEqBands(bands.map { it.toInt() })
                .build()
        }
    }

    suspend fun updateBass(value: Short) {
        context.dspDataStore.updateData { prefs ->
            prefs.toBuilder()
                .setBass(value.toInt())
                .build()
        }
    }

    suspend fun updateVirtualizer(value: Short) {
        context.dspDataStore.updateData { prefs ->
            prefs.toBuilder()
                .setVirtualizer(value.toInt())
                .build()
        }
    }

    suspend fun updateLoudness(value: Int) {
        context.dspDataStore.updateData { prefs ->
            prefs.toBuilder()
                .setLoudness(value)
                .build()
        }
    }
}

suspend fun updateEqBands(bands: List<Short>) {
    dataStore.updateData { config ->
        config.copy(eqBands = bands)
    }
}
