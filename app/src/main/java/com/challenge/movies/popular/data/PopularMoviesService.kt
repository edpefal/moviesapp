package com.challenge.movies.popular.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesService@Inject constructor(private val popularMoviesClient: PopularMoviesClient) {
    suspend fun getPopularMovies(page: Int): Flow<PopularMoviesResponse> {
        return popularMoviesClient.getPopularMovies(page)
    }
}