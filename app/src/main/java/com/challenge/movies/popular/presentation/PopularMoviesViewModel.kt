package com.challenge.movies.popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.movies.popular.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase): ViewModel() {

    private val _popularMoviesUiState = MutableStateFlow<PopularMoviesUiState>(PopularMoviesUiState.Loading)
    val popularMoviesUiState: StateFlow<PopularMoviesUiState> get() = _popularMoviesUiState

    fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            getPopularMoviesUseCase(page).collect { popularMovies ->
                if (popularMovies.isNotEmpty()) {
                    updateState(PopularMoviesUiState.Success(popularMovies))
                } else {
                   updateState(PopularMoviesUiState.Empty)
                }
            }

        }
    }

    fun updateState(newState: PopularMoviesUiState) {
        _popularMoviesUiState.value = newState
    }

}