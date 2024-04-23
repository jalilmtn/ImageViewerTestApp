package com.example.imageviewertestapp.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_HOME= "route_home"


fun NavGraphBuilder.homeScreen(navigateToFav: () -> Unit) {
    composable(
        route = ROUTE_HOME
    ) {
        HomeRoute(navigateToFav = navigateToFav)
    }
}