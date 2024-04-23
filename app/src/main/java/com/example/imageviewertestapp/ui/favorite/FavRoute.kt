package com.example.imageviewertestapp.ui.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FaveRoute(
    faveViewModel: FaveViewModel = hiltViewModel(),
) {
    val state by faveViewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        faveViewModel.getCatsFromLocal()
    })

    FaveScreen(
        state
    )
}






