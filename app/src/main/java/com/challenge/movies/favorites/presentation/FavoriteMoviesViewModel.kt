package com.challenge.movies.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.movies.favorites.domain.GetFavoriteMoviesUseCase
import com.challenge.movies.popular.domain.GetPopularMoviesUseCase
import com.challenge.movies.popular.presentation.PopularMoviesUiState
import com.challenge.movies.shared.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase): MoviesViewModel() {

    override fun getMovies() {
        updateUiState(PopularMoviesUiState.Loading)
        viewModelScope.launch {
            getFavoriteMoviesUseCase().collect { movies ->
                if (movies.isNotEmpty()) {
                    updateUiState(PopularMoviesUiState.Success(movies))
                } else {
                    updateUiState(PopularMoviesUiState.Empty)
                }
            }

        }
    }

    override fun updateUiState(newState: PopularMoviesUiState) {
        _popularMoviesUiState.value = newState
    }



}