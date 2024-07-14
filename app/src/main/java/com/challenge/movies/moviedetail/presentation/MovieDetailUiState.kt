package com.challenge.movies.moviedetail.presentation

import com.challenge.movies.popular.presentation.PopularMovieModel

sealed class MovieDetailUiState {
    data class Success(val movie: PopularMovieModel) : MovieDetailUiState()
    data object Error : MovieDetailUiState()
    data object Loading : MovieDetailUiState()
    data object Empty : MovieDetailUiState()

}