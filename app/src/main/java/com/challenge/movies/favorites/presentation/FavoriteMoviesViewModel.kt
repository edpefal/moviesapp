package com.challenge.movies.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.challenge.movies.favorites.domain.GetFavoriteMoviesUseCase
import com.challenge.movies.shared.presentation.models.MoviesUiState
import com.challenge.movies.shared.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase) :
    MoviesViewModel() {

    override fun getMovies() {
        viewModelScope.launch {
            getFavoriteMoviesUseCase().catch { updateUiState(MoviesUiState.Error) }
                .collect { movies ->
                    if (movies.isNotEmpty()) {
                        updateUiState(MoviesUiState.Success(movies))
                    } else {
                        updateUiState(MoviesUiState.Empty)
                    }
                }

        }
    }

    override fun updateUiState(newState: MoviesUiState) {
        _MoviesUiState.value = newState
    }


}