package com.example.common_ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.common_ui.model.Tab
import com.example.common_ui.theme.BlueAccent

@Composable
fun TMDBNavigationBar(
    navController: NavController,
    items: List<Tab>
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route

        items.forEach { tab ->
            val isSelected = currentRoute == tab.route

            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(
                            id = if (isSelected) tab.selectedIcon else tab.unselectedIcon
                        ),
                        contentDescription = "Icon NavBar",
                        colorFilter = if (isSelected) {
                            ColorFilter.tint(BlueAccent)
                        } else ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.tertiary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
                label = {
                    Text(
                        text = stringResource(id = tab.label),
                        textAlign = TextAlign.Center
                    )
                },
                selected = isSelected,
                onClick = {
                    if (currentRoute != tab.route) {
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}