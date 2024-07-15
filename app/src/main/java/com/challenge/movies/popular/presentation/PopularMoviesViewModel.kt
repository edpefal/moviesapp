package com.challenge.movies.popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.movies.popular.domain.GetPopularMoviesUseCase
import com.challenge.movies.shared.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase): MoviesViewModel() {

    //private val _popularMoviesUiState = MutableStateFlow<PopularMoviesUiState>(PopularMoviesUiState.Loading)
    //val popularMoviesUiState: StateFlow<PopularMoviesUiState> get() = _popularMoviesUiState

    override fun getMovies(page: Int?) {
        viewModelScope.launch {
            getPopularMoviesUseCase(page ?: 1).collect { popularMovies ->
                if (popularMovies.isNotEmpty()) {
                    updateUiState(PopularMoviesUiState.Success(popularMovies))
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