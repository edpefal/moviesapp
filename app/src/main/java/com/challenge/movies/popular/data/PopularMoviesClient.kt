package com.challenge.movies.popular.data

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesClient {
    @GET("/discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Flow<PopularMoviesResponse>
}