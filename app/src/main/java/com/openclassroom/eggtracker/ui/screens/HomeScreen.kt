package com.openclassroom.eggtracker.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.openclassroom.eggtracker.ui.theme.EggTrackerTheme


@Composable
fun HomeScreen( modifier: Modifier = Modifier) {
    Text(
        text = "Hello Android!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EggTrackerTheme {
        HomeScreen()
    }
}
