package com.example.imageviewertestapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToFav: () -> Unit,
) {
    val cats = homeViewModel.catsPagingFlow.collectAsLazyPagingItems()
    val dialogState = homeViewModel.dialogState.collectAsState()

    HomeScreen(
        cats,
        dialogState.value,
        homeViewModel::fave,
        homeViewModel::showImage,
        homeViewModel::hideDialog,
        navigateToFav
    )
}






