package com.challenge.movies.popular.domain

import com.challenge.movies.popular.data.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): Flow<List<PopularMovie>>
    suspend fun getMovieById(id: Long): Flow<PopularMovie>

}