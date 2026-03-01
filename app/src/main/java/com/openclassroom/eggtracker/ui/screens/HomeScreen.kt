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
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("EggTracker", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Text("Bienvenue dans votre application !", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

            // Boutons
            Button(onClick = {}) { Text("Primary Button") }
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Secondary Button") }

            // Carte
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Carte de test", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                    Text("Exemple de contenu pour vérifier le contraste et les couleurs.", color = MaterialTheme.colorScheme.onSurface)
                }
            }

            // Bouton accent vert
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = SoftGreen)
            ) {
                Text("Bouton accent vert", color = Color.White)
            }

            // Exemple de fond vert pour retour ou notification
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = SoftGreen.copy(alpha = 0.2f),
                tonalElevation = 2.dp
            ) {
                Text(
                    "Message d'accent vert (retour positif)",
                    modifier = Modifier.padding(16.dp),
                    color = DarkText
                )
            }

            // Champ texte
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Champ de texte") })

            // Switch
            var checked by remember { mutableStateOf(true) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Activer option")
                Switch(checked = checked, onCheckedChange = { checked = it })
            }

            // Liste d’éléments
            Column {
                repeat(5) { index ->
                    Text("Élément de liste $index", modifier = Modifier.padding(4.dp), color = MediumText)
                }
            }
        }

    }
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