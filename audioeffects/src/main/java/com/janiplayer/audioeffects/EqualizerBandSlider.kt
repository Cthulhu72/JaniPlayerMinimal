package com.janiplayer.audioeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EqualizerBandSlider(
    bandName: String,
    gain: Float,
    onGainChange: (Float) -> Unit,
    accentColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = bandName,
            style = MaterialTheme.typography.labelMedium,
            color = accentColor
        )

        Slider(
            value = gain,
            onValueChange = onGainChange,
            valueRange = -15f..15f,
            modifier = Modifier
                .height(200.dp)
                .width(40.dp),
            colors = SliderDefaults.colors(
                thumbColor = accentColor,
                activeTrackColor = accentColor.copy(alpha = 0.8f),
                inactiveTrackColor = accentColor.copy(alpha = 0.2f)
            )
        )
    }
}
