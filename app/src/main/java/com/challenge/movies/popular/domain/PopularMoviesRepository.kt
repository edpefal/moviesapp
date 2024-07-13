package com.challenge.movies.popular.domain

import com.challenge.movies.popular.data.PopularMovie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {

    suspend fun getPopularMovies(page: Int): Flow<List<PopularMovie>>

}