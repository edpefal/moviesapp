package com.challenge.moviedetail.presentation

import com.challenge.moviesmanager.presentation.MovieModel

sealed class MovieDetailUiState {
    data class Success(val movie: com.challenge.moviesmanager.presentation.MovieModel) : MovieDetailUiState()
    data object Error : MovieDetailUiState()
    data object Loading : MovieDetailUiState()
    data object Empty : MovieDetailUiState()

}