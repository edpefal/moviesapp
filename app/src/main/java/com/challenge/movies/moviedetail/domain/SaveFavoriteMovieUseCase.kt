package com.challenge.movies.moviedetail.domain

import com.challenge.moviesmanager.domain.MoviesRepository
import com.challenge.moviesmanager.presentation.MovieModel
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject constructor(private val movieRepository: com.challenge.moviesmanager.domain.MoviesRepository) {

    suspend operator fun invoke(movie: com.challenge.moviesmanager.presentation.MovieModel) {
        movieRepository.saveFavoriteMovie(movie)
    }

}