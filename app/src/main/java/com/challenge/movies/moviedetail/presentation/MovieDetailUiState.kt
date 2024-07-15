package com.challenge.movies.moviedetail.presentation

import com.challenge.movies.moviemanager.presentation.MovieModel

sealed class MovieDetailUiState {
    data class Success(val movie: MovieModel) : MovieDetailUiState()
    data object Error : MovieDetailUiState()
    data object Loading : MovieDetailUiState()
    data object Empty : MovieDetailUiState()

}