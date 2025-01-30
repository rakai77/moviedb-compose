package com.example.common_ui.navigation

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("home")
    data object Watchlist : AppRoute("watchlist")
    data object Cast : AppRoute("cast")
    data object MovieDetail : AppRoute("movie/{movieId}")
}