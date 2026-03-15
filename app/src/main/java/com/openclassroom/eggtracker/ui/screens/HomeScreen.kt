package com.openclassroom.eggtracker.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openclassroom.eggtracker.ui.theme.DarkText
import com.openclassroom.eggtracker.ui.theme.EggTrackerTheme
import com.openclassroom.eggtracker.ui.theme.MediumText
import com.openclassroom.eggtracker.ui.theme.SoftGreen

@Composable
fun HomeScreenContent() {
Text(text = "tableau de bord")
}

@Composable
fun HomeScreen() {
    // Applique ton thème en runtime, avec branding garanti
    EggTrackerTheme(darkTheme = isSystemInDarkTheme()) {
        HomeScreenContent()
    }
}

// --- Previews ---
@Preview(showBackground = true, name = "Light Theme Preview")
@Composable
fun LightThemePreview() {
    EggTrackerTheme(darkTheme = false) { // dynamicColor est supprimé dans ton thème
        HomeScreenContent()
    }
}

@Preview(showBackground = true, name = "Dark Theme Preview")
@Composable
fun DarkThemePreview() {
    EggTrackerTheme(darkTheme = true) { // dynamicColor est supprimé dans ton thème
        HomeScreenContent()
    }
}