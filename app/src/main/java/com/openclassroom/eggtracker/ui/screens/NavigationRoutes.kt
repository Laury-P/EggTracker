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
    val titleRes: Int,
    val icon: ImageVector
) {
    object HomeScreenNav : NavigationRoutes("home", R.string.nav_home, Icons.Filled.Home)
    object CoopScreenNav : NavigationRoutes("coop", R.string.nav_coops, Icons.Filled.AddHomeWork)
    object EggLogScreenNav : NavigationRoutes("egg_log", R.string.nav_egg_log, Icons.Filled.AddCircleOutline)
    object SettingsScreenNav : NavigationRoutes("settings", R.string.nav_settings, Icons.Filled.Settings)
    object StatisticsScreenNav : NavigationRoutes("statistic", R.string.nav_statistics, Icons.Filled.QueryStats)
}
