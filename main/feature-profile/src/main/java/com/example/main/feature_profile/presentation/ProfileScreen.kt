package com.example.main.feature_profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Profile Screen",
            style = LocalTextStyle.current.copy(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )

    }

}