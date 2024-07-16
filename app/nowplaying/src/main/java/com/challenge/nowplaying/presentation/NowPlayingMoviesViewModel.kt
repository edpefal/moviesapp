package com.challenge.nowplaying.presentation

import androidx.lifecycle.viewModelScope
import com.challenge.nowplaying.domain.GetNowPlayingMoviesUseCase
import com.challenge.shared.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase) :
    MoviesViewModel() {

    override fun getMovies() {
        if (currentPage <= totalPages) {
            updateUiState(com.challenge.shared.presentation.models.MoviesUiState.Loading)
            viewModelScope.launch {
                getNowPlayingMoviesUseCase(currentPage ?: 1)
                    .catch {
                        updateUiState(com.challenge.shared.presentation.models.MoviesUiState.Error)
                    }
                    .collect { movieList ->
                        currentMovies.addAll(movieList.results)
                        if (currentMovies.isNotEmpty()) {
                            updateUiState(com.challenge.shared.presentation.models.MoviesUiState.Success(currentMovies))
                            currentPage++
                            totalPages = movieList.totalPages
                        } else {
                            updateUiState(com.challenge.shared.presentation.models.MoviesUiState.Empty)
                        }
                    }

            }
        }
    }

    override fun updateUiState(newState: com.challenge.shared.presentation.models.MoviesUiState) {
        _MoviesUiState.value = newState

    }

}