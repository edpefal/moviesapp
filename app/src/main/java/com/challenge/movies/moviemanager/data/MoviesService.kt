package com.challenge.movies.moviemanager.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesService@Inject constructor(private val moviesClient: MoviesClient) {
    suspend fun getPopularMovies(page: Int): Flow<MoviesResponse> {
        return flow {
            emit(moviesClient.getPopularMovies(page))

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieById(id: Long): Flow<Movie> {
        return flow {
            emit(moviesClient.getMovieById(id))

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNowPlayingMovies(page: Int): Flow<MoviesResponse> {
        return flow {
            emit(moviesClient.getNowPlayingMovies(page))

        }.flowOn(Dispatchers.IO)
    }
}