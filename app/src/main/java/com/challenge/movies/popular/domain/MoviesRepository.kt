package com.challenge.movies.popular.domain

import com.challenge.movies.popular.data.PopularMovie
import com.challenge.movies.popular.presentation.PopularMovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): Flow<List<PopularMovie>>
    suspend fun getMovieById(id: Long): Flow<PopularMovie>
    suspend fun saveFavoriteMovie(movie: PopularMovieModel)
    suspend fun deleteFavoriteMovie(movieId: Long)
    suspend fun getFavoriteMovies(movie: PopularMovieModel): Flow<List<PopularMovieModel>>


}