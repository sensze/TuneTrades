package com.ifkusyoba.tunetrades.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class ItemNavigation(
    val title: String,
    val icon: ImageVector,
    val route: Screen,
)