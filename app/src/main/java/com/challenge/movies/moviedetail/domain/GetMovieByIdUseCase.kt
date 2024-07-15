package com.challenge.movies.moviedetail.domain

import com.challenge.movies.moviemanager.domain.MoviesRepository
import com.challenge.movies.moviemanager.presentation.MovieModel
import com.challenge.movies.shared.data.extensions.toPopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(id: Long): Flow<MovieModel> {
       return moviesRepository.getMovieById(id).map { movie ->
           movie.toPopularMovieModel()
       }
    }

}