package com.openclassroom.eggtracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.openclassroom.eggtracker.ui.screens.coop.CoopScreen
import com.openclassroom.eggtracker.ui.screens.EggLogScreen
import com.openclassroom.eggtracker.ui.screens.HomeScreen
import com.openclassroom.eggtracker.ui.screens.NavigationRoutes
import com.openclassroom.eggtracker.ui.screens.SettingsScreen
import com.openclassroom.eggtracker.ui.screens.StatisticsScreen
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
            EggTrackerTheme {
                MainScreen()
            }

        }
    }
}
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, currentDestination)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            EggTrackerNavHost(navController)
        }
    }

}

@Composable
fun BottomNavigationBar(navController: NavHostController, selectedDestination: NavDestination?) {
    val destination = listOf(NavigationRoutes.HomeScreenNav, NavigationRoutes.CoopScreenNav, NavigationRoutes.EggLogScreenNav, NavigationRoutes.SettingsScreenNav, NavigationRoutes.StatisticsScreenNav)

    NavigationBar() {
        destination.forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination?.route == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                        }
                },
                icon = {
                    Icon(destination.icon, contentDescription = stringResource(destination.titleRes))
                },
                label = {
                    Text(stringResource(destination.titleRes))
                }
            )
        }
    }
}

@Composable
fun EggTrackerNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.HomeScreenNav.route) {
        composable(NavigationRoutes.HomeScreenNav.route) {
            HomeScreen()
        }
        composable(NavigationRoutes.CoopScreenNav.route) {
            CoopScreen()
        }
        composable(NavigationRoutes.EggLogScreenNav.route) {
            EggLogScreen()
        }
        composable(NavigationRoutes.SettingsScreenNav.route) {
            SettingsScreen()
        }
        composable(NavigationRoutes.StatisticsScreenNav.route) {
            StatisticsScreen()
        }

    }

}
