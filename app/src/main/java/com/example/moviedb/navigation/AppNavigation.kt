package com.example.moviedb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.common_ui.navigation.AppRoute
import com.example.main.feature_cast.presentation.CastScreen
import com.example.main.feature_home.presentation.HomeScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = AppRoute.Home.route,
        modifier = modifier
    ) {
        composable(
            route = AppRoute.Home.route
        ) {
            HomeScreen(
                navHostController = navHostController
            )
        }

        composable(
            route = AppRoute.Cast.route
        ) {
            CastScreen(
                navHostController = navHostController
            )
        }
    }
    
}