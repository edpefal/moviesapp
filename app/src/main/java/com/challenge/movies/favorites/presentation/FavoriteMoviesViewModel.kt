package com.challenge.movies.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.movies.favorites.domain.GetFavoriteMoviesUseCase
import com.challenge.movies.popular.domain.GetPopularMoviesUseCase
import com.challenge.movies.popular.presentation.PopularMoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase): ViewModel() {

    private val _popularMoviesUiState = MutableStateFlow<PopularMoviesUiState>(PopularMoviesUiState.Loading)
    val popularMoviesUiState: StateFlow<PopularMoviesUiState> get() = _popularMoviesUiState

    fun getFavoriteMovies() {
        viewModelScope.launch {
            getFavoriteMoviesUseCase().collect { movies ->
                if (movies.isNotEmpty()) {
                    updateState(PopularMoviesUiState.Success(movies))
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