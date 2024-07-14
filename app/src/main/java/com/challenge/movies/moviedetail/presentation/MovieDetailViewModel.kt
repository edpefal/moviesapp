package com.challenge.movies.moviedetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.movies.moviedetail.domain.GetMovieByIdUseCase
import com.challenge.movies.moviedetail.domain.SaveFavoriteMovieUseCase
import com.challenge.movies.popular.presentation.PopularMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase
) : ViewModel() {

    private val _movieDetailUiState =
        MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val movieDetailUiState: StateFlow<MovieDetailUiState> get() = _movieDetailUiState

    fun getMovieDetail(id: Long) {
        viewModelScope.launch {
            getMovieByIdUseCase(id)
                .catch {
                    updateUiState(MovieDetailUiState.Error)
                }.collect {
                    updateUiState(MovieDetailUiState.Success(it))
                }
        }
    }

    fun saveFavoriteMovie(movie: PopularMovieModel) {
        viewModelScope.launch {
            saveFavoriteMovieUseCase(movie)
        }
    }

    fun updateUiState(newState: MovieDetailUiState) {
        _movieDetailUiState.value = newState
    }

}