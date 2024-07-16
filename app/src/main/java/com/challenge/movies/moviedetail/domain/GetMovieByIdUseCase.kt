package com.challenge.movies.moviedetail.domain

import com.challenge.moviesmanager.domain.MoviesRepository
import com.challenge.moviesmanager.presentation.MovieModel
import com.challenge.movies.shared.data.extensions.toPopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val moviesRepository: com.challenge.moviesmanager.domain.MoviesRepository) {

    suspend operator fun invoke(id: Long): Flow<com.challenge.moviesmanager.presentation.MovieModel> {
       return moviesRepository.getMovieById(id).map { movie ->
           movie.toPopularMovieModel()
       }
    }

}