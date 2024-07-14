package com.challenge.movies.moviedetail.domain

import com.challenge.movies.popular.domain.MoviesRepository
import com.challenge.movies.popular.presentation.PopularMovieModel
import com.challenge.movies.shared.data.extensions.toPopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(id: Long): Flow<PopularMovieModel> {
       return moviesRepository.getMovieById(id).map { movie ->
           movie.toPopularMovieModel()
       }
    }

}