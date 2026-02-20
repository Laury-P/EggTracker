package com.openclassroom.eggtracker.ui.screens



sealed class NavigationRoutes(
    val route: String,
) {
    object HomeScreen : NavigationRoutes("home_screen")
}
