package com.example.moviedb.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.common_ui.component.CustomNavigationBar
import com.example.common_ui.theme.MoviedbTheme
import com.example.moviedb.navigation.AppNavigation
import com.example.moviedb.navigation.bottomTabs
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            val mainViewModel: MainViewModel = koinViewModel()
            val state by mainViewModel.darkModeState.collectAsState()

            MoviedbTheme(darkTheme = state) {
                MovieDBApp()
            }
        }
    }
}

@Composable
fun MovieDBApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomNavigationBar(
                navController = navController,
                items = bottomTabs
            )
        }
    ) { paddingValues ->
        AppNavigation(
            modifier = Modifier.padding(paddingValues),
            navHostController = navController
        )
    }
}
