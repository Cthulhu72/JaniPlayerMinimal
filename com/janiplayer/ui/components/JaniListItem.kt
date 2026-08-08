package com.janiplayer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.janiplayer.ui.theme.UiDefaults

@Composable
fun JaniListItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(UiDefaults.itemPadding)
    ) {
        Icon(icon, contentDescription = null)
        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            subtitle?.let {
                Text(it, style = MaterialTheme.typography.bodySmall)
            }
        }

        if (selected) {
            Icon(Icons.Default.Check, contentDescription = null)
        }
    }
}
