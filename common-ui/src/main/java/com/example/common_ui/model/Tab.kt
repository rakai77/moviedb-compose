package com.example.common_ui.model

import androidx.annotation.DrawableRes

data class Tab(
    val route: String,
    val label: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)
