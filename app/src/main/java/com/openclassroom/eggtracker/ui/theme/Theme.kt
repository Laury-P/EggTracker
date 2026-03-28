package com.openclassroom.eggtracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkChickYellow,
    primaryContainer = Color(0xFF5A4B00),
    onPrimaryContainer = DarkChickYellow,
    secondary = SoftRed,
    secondaryContainer = SoftGreen.copy(alpha = 0.3f),
    background = DarkBackground,
    surface = DarkSurface,
    surfaceContainer = DarkSurface,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    outline = Color(0xFF2A2A2A),
)

private val LightColorScheme = lightColorScheme(
    primary = ChickYellow,
    primaryContainer = EggShell,
    onPrimaryContainer = DarkText,
    secondary = SoftRed,
    secondaryContainer = EggShell,
    background = Color(0xFFFFFCF5),
    surface = Color(0xFFFFF8EE),
    surfaceContainer = Color(0xFFFFF8EE),
    onPrimary = DarkText,
    onSecondary = PureWhite,
    onBackground = DarkText,
    onSurface = DarkText,
    outline = BorderGray,

)

@Composable
fun EggTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}