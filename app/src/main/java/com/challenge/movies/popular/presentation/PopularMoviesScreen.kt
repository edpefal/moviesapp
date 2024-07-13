package com.challenge.movies.popular.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun PopularMoviesScreen(popularMoviesViewModel: PopularMoviesViewModel) {
    val popularMovies by popularMoviesViewModel.popularMoviesUiState.collectAsState()
}