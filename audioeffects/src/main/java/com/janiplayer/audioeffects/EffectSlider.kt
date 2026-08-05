package com.janiplayer.audioeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EffectSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    title: String,
    accentColor: Color,
    range: ClosedFloatingPointRange<Float> = 0f..1f
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = accentColor
        )

        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = range,
            colors = SliderDefaults.colors(
                thumbColor = accentColor,
                activeTrackColor = accentColor.copy(alpha = 0.7f),
                inactiveTrackColor = accentColor.copy(alpha = 0.2f)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
    }
}
