package com.challenge.movies.moviemanager.domain

import com.challenge.movies.moviemanager.data.Movie
import com.challenge.movies.moviemanager.data.MoviesResponse
import com.challenge.movies.moviemanager.data.db.entities.FavoriteMovieEntity
import com.challenge.movies.moviemanager.presentation.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): Flow<MoviesResponse>
    suspend fun getMovieById(id: Long): Flow<Movie>
    suspend fun saveFavoriteMovie(movie: MovieModel)
    suspend fun deleteFavoriteMovie(movieId: Long)
    suspend fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    suspend fun getNowPlayingMovies(page: Int): Flow<MoviesResponse>


}