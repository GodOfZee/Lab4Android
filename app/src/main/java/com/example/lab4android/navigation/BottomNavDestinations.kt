package com.example.lab4android.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lab4android.R

enum class BottomNavDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home, Icons.Default.Home, "home"),
    ABOUT(R.string.about, Icons.Default.Info, "about"),
    SETTINGS(R.string.settings, Icons.Default.Settings, "settings")
}