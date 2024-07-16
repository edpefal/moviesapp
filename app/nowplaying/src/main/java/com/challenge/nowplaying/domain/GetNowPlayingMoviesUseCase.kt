package com.challenge.nowplaying.domain

import com.challenge.moviesmanager.domain.MoviesRepository
import com.challenge.moviesmanager.presentation.MovieListModel
import com.challenge.moviesmanager.extensions.toPopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(page: Int): Flow<MovieListModel> {
        return moviesRepository.getNowPlayingMovies(page).map { popularMovieResponse ->
            val results = popularMovieResponse.results?.map { it.toPopularMovieModel() }
            MovieListModel(
                totalPages = popularMovieResponse.totalPages ?: 0,
                results = results ?: emptyList()
            )
        }
    }

}