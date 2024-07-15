package com.challenge.movies.shared.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.challenge.movies.popular.presentation.PopularMovieModel
import com.challenge.movies.popular.presentation.PopularMoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MoviesViewModel: ViewModel() {

    protected val _popularMoviesUiState = MutableStateFlow<PopularMoviesUiState>(PopularMoviesUiState.Loading)
    val popularMoviesUiState: StateFlow<PopularMoviesUiState> get() = _popularMoviesUiState

    abstract fun getMovies(page: Int?)
    abstract fun updateUiState(newState: PopularMoviesUiState)
}