package com.challenge.movies.popular.data

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PopularMoviesClient {
    @GET("/popular")
    suspend fun getPopularMovies(): Flow<PopularMoviesResponse>
}