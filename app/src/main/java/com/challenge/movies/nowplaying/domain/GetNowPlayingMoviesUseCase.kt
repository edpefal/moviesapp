package com.challenge.movies.nowplaying.domain

import com.challenge.movies.popular.data.PopularMovie
import com.challenge.movies.popular.domain.MoviesRepository
import com.challenge.movies.popular.presentation.PopularMovieModel
import com.challenge.movies.shared.data.extensions.toPopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(page: Int): Flow<List<PopularMovieModel>> {
        return moviesRepository.getNowPlayingMovies(page).map { popularMoviesList ->
            popularMoviesList.map { it.toPopularMovieModel() }
        }
    }

}