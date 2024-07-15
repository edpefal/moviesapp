package com.challenge.movies.moviedetail.domain

import com.challenge.movies.moviemanager.domain.MoviesRepository
import com.challenge.movies.moviemanager.presentation.MovieModel
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject constructor(private val movieRepository: MoviesRepository) {

    suspend operator fun invoke(movie: MovieModel) {
        movieRepository.saveFavoriteMovie(movie)
    }

}