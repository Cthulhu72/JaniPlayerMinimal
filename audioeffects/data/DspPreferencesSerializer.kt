package com.janiplayer.audioeffects.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.janiplayer.audioeffects.data.DspPreferences
import java.io.InputStream
import java.io.OutputStream

object DspPreferencesSerializer : Serializer<DspPreferences> {

    override val defaultValue: DspPreferences = DspPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): DspPreferences {
        try {
            return DspPreferences.parseFrom(input)
        } catch (e: Exception) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: DspPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}
