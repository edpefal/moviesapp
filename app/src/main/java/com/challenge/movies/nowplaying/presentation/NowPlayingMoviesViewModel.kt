package com.challenge.movies.nowplaying.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.movies.nowplaying.domain.GetNowPlayingMoviesUseCase
import com.challenge.movies.popular.domain.GetPopularMoviesUseCase
import com.challenge.movies.popular.presentation.PopularMoviesUiState
import com.challenge.movies.shared.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase) :
    MoviesViewModel() {

    override fun getMovies(page: Int?) {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase(page ?: 1).collect { popularMovies ->
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