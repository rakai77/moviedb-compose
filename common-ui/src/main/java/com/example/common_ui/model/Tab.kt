package com.example.common_ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tab(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)
