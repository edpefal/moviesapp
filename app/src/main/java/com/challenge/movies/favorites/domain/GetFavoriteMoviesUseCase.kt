package com.challenge.movies.favorites.domain

import com.challenge.movies.moviemanager.data.db.entities.FavoriteMovieEntity
import com.challenge.movies.moviemanager.domain.MoviesRepository
import com.challenge.movies.moviemanager.presentation.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): Flow<List<MovieModel>> = moviesRepository.getFavoriteMovies().map {
        it.map { movie ->
            movie.toPopularMovieModel()
        }
    }

    fun FavoriteMovieEntity.toPopularMovieModel() = MovieModel(
        id = id,
        title = title,
        poster = posterPath,
        overview = overview,
        releaseDate = releaseDate
    )

}