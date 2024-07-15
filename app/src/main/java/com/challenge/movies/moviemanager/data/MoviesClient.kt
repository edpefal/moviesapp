package com.challenge.movies.moviemanager.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesClient {
    @GET("3/discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): MoviesResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") id: Long,
    ): Movie

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
    ): MoviesResponse
}