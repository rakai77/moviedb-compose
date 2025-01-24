package com.example.moviedb.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.common_ui.component.TMDBNavigationBar
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

            MoviedbTheme {
                MovieDBApp()
            }
        }
    }
}

@Composable
fun MovieDBApp() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.BottomCenter
    ) {

        AppNavigation(navHostController = navController)

        TMDBNavigationBar(
            navController = navController,
            items = bottomTabs
        )
    }
}
