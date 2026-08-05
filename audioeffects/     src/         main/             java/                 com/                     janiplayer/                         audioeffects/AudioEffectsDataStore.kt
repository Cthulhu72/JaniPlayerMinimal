package com.janiplayer.audioeffects

import android.content.Context
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

private val Context.dataStore by preferencesDataStore("audio_effects")

class AudioEffectsDataStore(private val context: Context) {

    // EQ sávok (0–9)
    private val bandKeys = (0..9).map { floatPreferencesKey("eq_band_$it") }

    // Preset
    private val presetKey = stringPreferencesKey("eq_preset")

    // Bass / Virtualizer / Loudness
    private val bassKey = floatPreferencesKey("bass_boost")
    private val virtualizerKey = floatPreferencesKey("virtualizer")
    private val loudnessKey = floatPreferencesKey("loudness")

    // --- READ ---
    val savedState: Flow<SavedEffectsState> = context.dataStore.data.map { prefs ->
        SavedEffectsState(
            bands = bandKeys.map { prefs[it] ?: 0f },
            preset = prefs[presetKey] ?: "Normal",
            bassBoost = prefs[bassKey] ?: 0f,
            virtualizer = prefs[virtualizerKey] ?: 0f,
            loudness = prefs[loudnessKey] ?: 0f
        )
    }

    // --- WRITE ---
    suspend fun saveBand(index: Int, gain: Float) {
        context.dataStore.edit { prefs ->
            prefs[bandKeys[index]] = gain
        }
    }

    suspend fun savePreset(name: String) {
        context.dataStore.edit { prefs ->
            prefs[presetKey] = name
        }
    }

    suspend fun saveBass(value: Float) {
        context.dataStore.edit { prefs ->
            prefs[bassKey] = value
        }
    }

    suspend fun saveVirtualizer(value: Float) {
        context.dataStore.edit { prefs ->
            prefs[virtualizerKey] = value
        }
    }

    suspend fun saveLoudness(value: Float) {
        context.dataStore.edit { prefs ->
            prefs[loudnessKey] = value
        }
    }
}

data class SavedEffectsState(
    val bands: List<Float>,
    val preset: String,
    val bassBoost: Float,
    val virtualizer: Float,
    val loudness: Float
)
