package com.openclassroom.eggtracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.openclassroom.eggtracker.ui.screens.HomeScreen
import com.openclassroom.eggtracker.ui.screens.NavigationRoutes
import com.openclassroom.eggtracker.ui.theme.EggTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            )
        )
        setContent {
            val navController = rememberNavController()

            EggTrackerTheme {
                EggTrackerNavHost(navController = navController)
            }

        }
    }
}

@Composable
fun EggTrackerNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.HomeScreen.route) {
        composable(NavigationRoutes.HomeScreen.route) {
            HomeScreen()
        }
    }

}
