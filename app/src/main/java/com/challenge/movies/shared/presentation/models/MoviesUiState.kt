package com.challenge.movies.shared.presentation.models

import com.challenge.movies.moviemanager.presentation.MovieModel

sealed class MoviesUiState {
    data class Success(val movies: List<MovieModel>) : MoviesUiState()
    data object Error : MoviesUiState()
    data object Loading : MoviesUiState()
    data object Empty : MoviesUiState()

}