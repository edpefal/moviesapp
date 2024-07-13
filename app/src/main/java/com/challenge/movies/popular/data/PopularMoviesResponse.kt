package com.challenge.movies.popular.data

data class PopularMoviesResponse(
    val page: Long? = null,
    val results: List<PopularMovie>? = null,
    val totalPages: Long? = null,
    val totalResults: Long? = null
)