package com.challenge.movies.popular.presentation

sealed class PopularMoviesUiState {
    data class Success(val movies: List<PopularMovieModel>) : PopularMoviesUiState()
    data object Error : PopularMoviesUiState()
    data object Loading : PopularMoviesUiState()
    data object Empty : PopularMoviesUiState()

}