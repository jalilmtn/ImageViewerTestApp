package com.example.imageviewertestapp.ui.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_FAVE = "route_fave"


fun NavController.navigateToFav() {
    this.navigate(ROUTE_FAVE)
}


fun NavGraphBuilder.faveScreen() {
    composable(
        route = ROUTE_FAVE
    ) {
        FaveRoute()
    }
}