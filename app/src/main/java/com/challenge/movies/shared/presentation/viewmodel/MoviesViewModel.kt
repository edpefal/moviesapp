package com.challenge.movies.shared.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.challenge.movies.popular.presentation.PopularMovieModel
import com.challenge.movies.popular.presentation.PopularMoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MoviesViewModel: ViewModel() {

    protected val _popularMoviesUiState = MutableStateFlow<PopularMoviesUiState>(PopularMoviesUiState.Loading)
    val popularMoviesUiState: StateFlow<PopularMoviesUiState> get() = _popularMoviesUiState

    protected var currentPage = 1
    protected var totalPages = 1
    protected val currentMovies = mutableListOf<PopularMovieModel>()

    abstract fun getMovies()
    abstract fun updateUiState(newState: PopularMoviesUiState)

}