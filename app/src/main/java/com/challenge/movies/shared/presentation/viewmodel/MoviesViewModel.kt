package com.challenge.movies.shared.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.challenge.movies.moviemanager.presentation.MovieModel
import com.challenge.movies.shared.presentation.models.MoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MoviesViewModel: ViewModel() {

    protected val _MoviesUiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val moviesUiState: StateFlow<MoviesUiState> get() = _MoviesUiState

    protected var currentPage = 1
    protected var totalPages = 1
    protected val currentMovies = mutableListOf<MovieModel>()

    abstract fun getMovies()
    abstract fun updateUiState(newState: MoviesUiState)

}