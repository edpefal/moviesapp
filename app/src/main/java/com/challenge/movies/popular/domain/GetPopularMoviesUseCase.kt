package com.challenge.movies.popular.domain

import com.challenge.movies.popular.data.PopularMovie
import com.challenge.movies.popular.presentation.PopularMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {

    suspend operator fun invoke(page: Int): Flow<List<PopularMovieModel>> {
        return popularMoviesRepository.getPopularMovies(page).map { popularMoviesList ->
            popularMoviesList.map { it.toPopularMovieModel() }
        }
    }

    private fun PopularMovie.toPopularMovieModel(): PopularMovieModel {
        return PopularMovieModel(
            id = this.id ?: 0,
            title = title.orEmpty(),
            poster = posterPath.orEmpty(),
            overview = overview.orEmpty(),
            releaseDate = releaseDate.orEmpty()
        )
    }


}