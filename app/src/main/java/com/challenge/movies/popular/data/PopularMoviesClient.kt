package com.challenge.movies.popular.data

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularMoviesClient {
    @GET("3/discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): PopularMoviesResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") id: Long,
    ): PopularMovie

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
    ): PopularMoviesResponse
}