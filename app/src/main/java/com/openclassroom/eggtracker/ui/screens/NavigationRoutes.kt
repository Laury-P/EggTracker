package com.openclassroom.eggtracker.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.AddHomeWork
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.openclassroom.eggtracker.R


sealed class NavigationRoutes(
    val route: String,
) {
    object HomeScreenNav : NavigationRoutes("home")
    object CoopScreenNav : NavigationRoutes("coop")
    object AddEditCoopScreenNav : NavigationRoutes("add_edit_coop")
    object EggLogScreenNav : NavigationRoutes("egg_log")
    object SettingsScreenNav : NavigationRoutes("settings")
    object StatisticsScreenNav : NavigationRoutes("statistic")

    companion object {
        val bottomNavItems = listOf(
            BottomNavItem(HomeScreenNav, R.string.nav_home, Icons.Filled.Home),
            BottomNavItem(CoopScreenNav, R.string.nav_coops, Icons.Filled.AddHomeWork),
            BottomNavItem(EggLogScreenNav, R.string.nav_egg_log, Icons.Filled.AddCircleOutline),
            BottomNavItem(SettingsScreenNav, R.string.nav_settings, Icons.Filled.Settings),
            BottomNavItem(StatisticsScreenNav, R.string.nav_statistics, Icons.Filled.QueryStats)
        )
    }

}

data class BottomNavItem(
    val navRoute: NavigationRoutes,
    val titleRes: Int,
    val icon: ImageVector
)
