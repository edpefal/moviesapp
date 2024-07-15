package com.challenge.movies.popular.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularMoviesService@Inject constructor(private val popularMoviesClient: PopularMoviesClient) {
    suspend fun getPopularMovies(page: Int): Flow<PopularMoviesResponse> {
        return flow {
            emit(popularMoviesClient.getPopularMovies(page))

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieById(id: Long): Flow<PopularMovie> {
        return flow {
            emit(popularMoviesClient.getMovieById(id))

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNowPlayingMovies(page: Int): Flow<PopularMoviesResponse> {
        return flow {
            emit(popularMoviesClient.getNowPlayingMovies(page))

        }.flowOn(Dispatchers.IO)
    }
}