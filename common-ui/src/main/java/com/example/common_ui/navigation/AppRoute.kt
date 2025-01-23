package com.example.common_ui.navigation

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("home")
    data object Profile : AppRoute("profile")
    data object MovieDetail : AppRoute("movie/{movieId}")
}