package com.janiplayer.audioeffects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PresetSelector(
    presets: List<String>,
    selectedPreset: String,
    onPresetSelected: (String) -> Unit,
    accentColor: Color
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 8.dp)
    ) {
        presets.forEach { preset ->
            Surface(
                color = if (preset == selectedPreset)
                    accentColor.copy(alpha = 0.2f)
                else
                    MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium,
                tonalElevation = if (preset == selectedPreset) 4.dp else 0.dp,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { onPresetSelected(preset) }
            ) {
                Text(
                    text = preset,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = if (preset == selectedPreset) accentColor else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
