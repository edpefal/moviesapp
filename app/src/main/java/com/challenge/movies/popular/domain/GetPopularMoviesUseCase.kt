package com.challenge.movies.popular.domain

import com.challenge.movies.popular.data.PopularMovie
import com.challenge.movies.popular.presentation.PopularMovieModel
import com.challenge.movies.shared.data.extensions.toPopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(page: Int): Flow<List<PopularMovieModel>> {
        return moviesRepository.getPopularMovies(page).map { popularMoviesList ->
            popularMoviesList.map { it.toPopularMovieModel() }
        }
    }

}