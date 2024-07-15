package com.challenge.movies.favorites.domain

import com.challenge.movies.popular.data.db.entities.FavoriteMovieEntity
import com.challenge.movies.popular.domain.MoviesRepository
import com.challenge.movies.popular.presentation.PopularMovieModel
import com.challenge.movies.shared.data.extensions.toPopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): Flow<List<PopularMovieModel>> = moviesRepository.getFavoriteMovies().map {
        it.map { movie ->
            movie.toPopularMovieModel()
        }
    }

    fun FavoriteMovieEntity.toPopularMovieModel() = PopularMovieModel(
        id = id,
        title = title,
        poster = posterPath,
        overview = overview,
        releaseDate = releaseDate
    )

}