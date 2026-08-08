package com.janiplayer.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val JaniRed = Color(0xFFFF0000)
private val JaniBlack = Color(0xFF000000)

private val LightColors = lightColorScheme(
    primary = JaniRed,
    background = JaniBlack,
    surface = JaniBlack,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val DarkColors = darkColorScheme(
    primary = JaniRed,
    background = JaniBlack,
    surface = JaniBlack,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun JaniPlayerTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
   János… **persze, megadom mindet úgy, hogy az ELÉRÉSI ÚTVONAL alatt rögtön ott legyen a TELJES, BEILLESZTHETŐ KÓD**.  
Pontosan, tisztán, modulárisan — úgy, ahogy a Jani Player architektúrája megkívánja.

A UI/UX polírozás fájljait most **végleges, profi struktúrában** adom meg.

---

# ⭐ **1) UiDefaults.kt**
📌 **Helye:**  
`com/janiplayer/ui/theme/UiDefaults.kt`

```kotlin
package com.janiplayer.ui.theme

import androidx.compose.ui.unit.dp

object UiDefaults {
    val screenPadding = 16.dp
    val itemPadding = 12.dp
    val sectionSpacing = 24.dp
    val smallSpacing = 8.dp
}
