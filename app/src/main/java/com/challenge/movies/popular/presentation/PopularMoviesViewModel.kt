package com.challenge.movies.popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.movies.popular.domain.GetPopularMoviesUseCase
import com.challenge.movies.shared.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    MoviesViewModel() {
    override fun getMovies() {
        if (currentPage <= totalPages) {
            updateUiState(PopularMoviesUiState.Loading)
            viewModelScope.launch {
                getPopularMoviesUseCase(currentPage ?: 1)
                    .catch {
                        updateUiState(PopularMoviesUiState.Error)
                    }
                    .collect { movieList ->
                        currentMovies.addAll(movieList.results)
                        if (currentMovies.isNotEmpty()) {
                            updateUiState(PopularMoviesUiState.Success(currentMovies))
                            currentPage++
                            totalPages = movieList.totalPages
                        } else {
                            updateUiState(PopularMoviesUiState.Empty)
                        }
                    }

            }
        }
    }

    override fun updateUiState(newState: PopularMoviesUiState) {
        _popularMoviesUiState.value = newState

    }

    fun loadMoreMovies(lastVisibleItemPosition: Int) {
        if (lastVisibleItemPosition == currentMovies.size - 1) {
            getMovies()
        }
    }

}