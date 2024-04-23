package com.example.imageviewertestapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.imageviewertestapp.ui.favorite.faveScreen
import com.example.imageviewertestapp.ui.favorite.navigateToFav
import com.example.imageviewertestapp.ui.home.ROUTE_HOME
import com.example.imageviewertestapp.ui.home.homeScreen

@Composable
fun ImageViewerNavHost(modifier: Modifier, navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = ROUTE_HOME) {
        homeScreen(navController::navigateToFav)
        faveScreen()
    }
}