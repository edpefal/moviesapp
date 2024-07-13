package com.challenge.movies.popular.data

data class PopularMoviesResponse(
    val page: Int? = null,
    val results: List<PopularMovie>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)