package com.challenge.moviesmanager.domain

import com.challenge.moviesmanager.data.Movie
import com.challenge.moviesmanager.data.MoviesResponse
import com.challenge.moviesmanager.data.db.entities.FavoriteMovieEntity
import com.challenge.moviesmanager.presentation.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): Flow<MoviesResponse>
    suspend fun getMovieById(id: Long): Flow<Movie>
    suspend fun saveFavoriteMovie(movie: MovieModel)
    suspend fun deleteFavoriteMovie(movieId: Long)
    suspend fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    suspend fun getNowPlayingMovies(page: Int): Flow<MoviesResponse>


}