package com.challenge.favorites.domain

import com.challenge.moviesmanager.data.db.entities.FavoriteMovieEntity
import com.challenge.moviesmanager.domain.MoviesRepository
import com.challenge.moviesmanager.presentation.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val moviesRepository: com.challenge.moviesmanager.domain.MoviesRepository) {

    suspend operator fun invoke(): Flow<List<MovieModel>> =
        moviesRepository.getFavoriteMovies().map {
            it.map { movie ->
                movie.toPopularMovieModel()
            }
        }

    private fun FavoriteMovieEntity.toPopularMovieModel() =
        MovieModel(
            id = id,
            title = title,
            poster = posterPath,
            overview = overview,
            releaseDate = releaseDate,
            popularity = popularity,
            languages = languages,
            voteAverage = voteAverage
        )

}