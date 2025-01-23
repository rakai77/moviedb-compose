package com.example.moviedb.navigation

import com.example.common_ui.R
import com.example.common_ui.model.Tab
import com.example.common_ui.navigation.AppRoute

val bottomTabs = listOf(
    Tab(
        route = AppRoute.Home.route,
        label = "Home Screen",
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined,
    ),
    Tab(
        route = AppRoute.Profile.route,
        label = "Profile Screen",
        selectedIcon = R.drawable.ic_profile_filled,
        unselectedIcon = R.drawable.ic_profile_outlined,
    )
)