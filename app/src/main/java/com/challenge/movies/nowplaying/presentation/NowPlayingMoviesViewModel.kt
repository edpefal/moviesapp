package com.challenge.movies.nowplaying.presentation

import androidx.lifecycle.viewModelScope
import com.challenge.movies.nowplaying.domain.GetNowPlayingMoviesUseCase
import com.challenge.movies.shared.presentation.models.MoviesUiState
import com.challenge.movies.shared.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase) :
    MoviesViewModel() {

    override fun getMovies() {
        if (currentPage <= totalPages) {
            updateUiState(MoviesUiState.Loading)
            viewModelScope.launch {
                getNowPlayingMoviesUseCase(currentPage ?: 1)
                    .catch {
                        updateUiState(MoviesUiState.Error)
                    }
                    .collect { movieList ->
                        currentMovies.addAll(movieList.results)
                        if (currentMovies.isNotEmpty()) {
                            updateUiState(MoviesUiState.Success(currentMovies))
                            currentPage++
                            totalPages = movieList.totalPages
                        } else {
                            updateUiState(MoviesUiState.Empty)
                        }
                    }

            }
        }
    }

    override fun updateUiState(newState: MoviesUiState) {
        _MoviesUiState.value = newState

    }

}