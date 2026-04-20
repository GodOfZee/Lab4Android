package com.example.lab4android.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lab4android.R

enum class ScreenDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home, Icons.Default.Home, "home"),
    CATEGORY(R.string.categories, Icons.Default.Category, "category"),
    DETAILS(R.string.details, Icons.Default.Info, "details"),
    ABOUT(R.string.about, Icons.Default.Info, "about"),
    SETTINGS(R.string.settings, Icons.Default.Settings, "settings")
}