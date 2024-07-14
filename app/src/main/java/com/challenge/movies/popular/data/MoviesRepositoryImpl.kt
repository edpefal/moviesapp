package com.challenge.movies.popular.data

import com.challenge.movies.popular.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val popularMoviesService: PopularMoviesService): MoviesRepository {

    override suspend fun getPopularMovies(page: Int): Flow<List<PopularMovie>> {
        return popularMoviesService.getPopularMovies(page).map { it.results ?: emptyList() }
    }

    override suspend fun getMovieById(id: Long): Flow<PopularMovie> {
        return popularMoviesService.getMovieById(id)
    }

}