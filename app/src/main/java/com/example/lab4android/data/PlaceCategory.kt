package com.example.lab4android.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lab4android.R

enum class PlaceCategory(
    @StringRes val label: Int,
    val icon: ImageVector
) {
    COFFEE(R.string.coffee, Icons.Default.Coffee),
    PARKS(R.string.parks, Icons.Default.Park),
    RESTAURANTS(R.string.restaurants, Icons.Default.Restaurant),
    MUSEUMS(R.string.museums, Icons.Default.Museum),
    SHOPPING(R.string.shopping, Icons.Default.ShoppingCart)
}