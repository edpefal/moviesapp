package com.challenge.movies.popular.data

import com.challenge.movies.popular.domain.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(private val popularMoviesService: PopularMoviesService): PopularMoviesRepository {

    override suspend fun getPopularMovies(page: Int): Flow<List<PopularMovie>> {
        return popularMoviesService.getPopularMovies(page).map { it.results ?: emptyList() }
    }

}