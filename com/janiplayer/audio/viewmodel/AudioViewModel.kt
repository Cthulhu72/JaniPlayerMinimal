package com.janiplayer.audio.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.Player
import com.janiplayer.audio.player.OptimizedAudioPlayer
import com.janiplayer.audio.dsp.AudioEffects
import kotlinx.coroutines.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AudioUiState(
    val isPlaying: Boolean = false,
    val positionMs: Long = 0L,
    val durationMs: Long = 0L,
    val audioSessionId: Int = 0,
    val volume: Float = 1f
)

class AudioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AudioUiState())
    val uiState: StateFlow<AudioUiState> = _uiState

    private var player: ExoPlayer? = null
    private var effects: AudioEffects? = null

    /**
     * Initialize player lazily. Safe to call multiple times.
     */
    fun initPlayer(context: Context) {
        if (player != null) return

        player = OptimizedAudioPlayer.create(context).also { p ->
            p.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    _uiState.update { it.copy(isPlaying = isPlaying) }
                }

                override fun onPlaybackStateChanged(state: Int) {
                    val duration = if (state == Player.STATE_READY) p.duration else _uiState.value.durationMs
                    _uiState.update { it.copy(durationMs = if (duration >= 0) duration else 0L) }
                }
            })

            // audioSessionId may be 0 until player has audio output; update when available
            val sessionId = p.audioSessionId
            if (sessionId > 0) {
                effects = AudioEffects(sessionId)
                _uiState.update { it.copy(audioSessionId = sessionId) }
            } else {
                // schedule a short check after prepare/play to pick up session id
                viewModelScope.launch {
                    delay(300)
                    val sid = p.audioSessionId
                    if (sid > 0) {
                        effects = AudioEffects(sid)
                        _uiState.update { it.copy(audioSessionId = sid) }
                    }
                }
            }

            // ensure volume state sync
            _uiState.update { it.copy(volume = p.volume) }

            // start ticker to update position periodically
            startPositionTicker()
        }
    }

    fun playUri(uri: String) {
        player?.let { OptimizedAudioPlayer.prepareAndPlay(it, uri) }
    }

    fun pause() {
        player?.let { OptimizedAudioPlayer.pause(it) }
    }

    fun stop() {
        player?.let { OptimizedAudioPlayer.stop(it) }
    }

    fun setVolume(v: Float) {
        player?.volume = v
        _uiState.update { it.copy(volume = v) }
    }

    fun applyEqPreset(bandLevels: ShortArray) {
        val e = effects ?: return
        for (i in bandLevels.indices) {
            e.setEqBandLevel(i.toShort(), bandLevels[i])
        }
    }

    fun setBass(strength: Short) {
        effects?.setBassStrength(strength)
    }

    fun setVirtualizer(strength: Short) {
        effects?.setVirtualizerStrength(strength)
    }

    private fun startPositionTicker() {
        viewModelScope.launch {
            while (isActive) {
                val pos = player?.currentPosition ?: 0L
                _uiState.update { it.copy(positionMs = pos) }
                delay(250)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        try {
            effects?.release()
            player?.release()
        } catch (_: Throwable) { }
        player = null
        effects = null
    }
}
