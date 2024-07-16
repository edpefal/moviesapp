package com.challenge.popular.domain


import com.challenge.moviesmanager.extensions.toPopularMovieModel
import com.challenge.moviesmanager.presentation.MovieListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: com.challenge.moviesmanager.domain.MoviesRepository) {

    suspend operator fun invoke(page: Int): Flow<MovieListModel> {
        return moviesRepository.getPopularMovies(page).map { popularMovieResponse ->
            val results = popularMovieResponse.results?.map { it.toPopularMovieModel() }
            MovieListModel(
                totalPages = popularMovieResponse.totalPages ?: 0,
                results = results ?: emptyList()
            )
        }
    }

}