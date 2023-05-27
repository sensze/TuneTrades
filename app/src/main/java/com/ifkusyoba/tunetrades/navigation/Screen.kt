package com.ifkusyoba.tunetrades.navigation

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Cart : Screen("Cart")
    object Account : Screen("Account")
    object DetailProduct : Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
}