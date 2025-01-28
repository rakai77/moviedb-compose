package com.example.moviedb.navigation

import com.example.common_ui.R
import com.example.common_ui.model.Tab
import com.example.common_ui.navigation.AppRoute

val bottomTabs = listOf(
    Tab(
        route = AppRoute.Home.route,
        label = R.string.home_label,
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined,
    ),
    Tab(
        route = AppRoute.Cast.route,
        label = R.string.cast_label,
        selectedIcon = R.drawable.ic_profile_filled,
        unselectedIcon = R.drawable.ic_profile_outlined,
    )
)