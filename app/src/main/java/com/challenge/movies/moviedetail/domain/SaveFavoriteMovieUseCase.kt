package com.challenge.movies.moviedetail.domain

import com.challenge.movies.popular.domain.MoviesRepository
import com.challenge.movies.popular.presentation.PopularMovieModel
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject constructor(private val movieRepository: MoviesRepository) {

    suspend operator fun invoke(movie: PopularMovieModel) {
        movieRepository.saveFavoriteMovie(movie)
    }

}